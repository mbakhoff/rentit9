package esi.rentit9.rest.controller;

import esi.rentit9.RBAC;
import esi.rentit9.domain.OrderStatus;
import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.dto.PurchaseOrderResource;
import esi.rentit9.dto.PurchaseOrderResourceAssembler;
import esi.rentit9.dto.PurchaseOrderResourceList;
import esi.rentit9.rest.util.HttpHelpers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/rest/")
public class PurchaseOrderRestController {

    private final PurchaseOrderResourceAssembler assembler;

    public PurchaseOrderRestController() {
        assembler = new PurchaseOrderResourceAssembler();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ex.printStackTrace();
        if (ex instanceof RBAC.UnauthorizedAccessException) {
            return new ResponseEntity<String>(HttpHelpers.getStack(ex), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<String>(HttpHelpers.getStack(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "pos", method = RequestMethod.GET)
    public ResponseEntity<PurchaseOrderResourceList> getAll() {
        List<PurchaseOrder> orders = PurchaseOrder.findAllPurchaseOrders();

        PurchaseOrderResourceList resourceList = new PurchaseOrderResourceList(assembler.toResource(orders));
        return new ResponseEntity<PurchaseOrderResourceList>(resourceList, HttpStatus.OK);
    }

    @RequestMapping(value = "pos", method = RequestMethod.POST)
    public ResponseEntity<PurchaseOrderResource> createOrder(@RequestBody PurchaseOrderResource res) {
        RBAC.assertAuthority(RBAC.ROLE_CLIENT);

        if (hasMissingFields(res)) {
            throw new IllegalArgumentException("some of the required fields are missing");
        }
        if (Plant.findPlant(Long.parseLong(res.getPlantId())) == null) {
            throw new IllegalArgumentException("plant not found");
        }

        PurchaseOrder order = new PurchaseOrder();
        assembler.fromResource(order, res);
        order.setStatus(OrderStatus.CREATED);
        order.persist();

        HttpHeaders headers = new HttpHeaders();
        headers.add("EntityId", order.getId().toString());
        PurchaseOrderResource resources = assembler.toResource(order);
        return new ResponseEntity<PurchaseOrderResource>(resources, headers, HttpStatus.CREATED);
    }

    private static boolean hasMissingFields(PurchaseOrderResource res) {
        return res.getBuildit() == null ||
                res.getBuilditOrderId() == null ||
                res.getStartDate() == null ||
                res.getEndDate() == null ||
                res.getTotal() == null ||
                res.getPlantId() == null ||
                res.getSiteAddress() == null;
    }

    @RequestMapping(value = "pos/{id}", method = RequestMethod.GET)
    public ResponseEntity<PurchaseOrderResource> getById(@PathVariable Long id) {
        PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);

        PurchaseOrderResource resources = assembler.toResource(order);
        return new ResponseEntity<PurchaseOrderResource>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "pos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        RBAC.assertAuthority(RBAC.ROLE_ADMIN, RBAC.ROLE_CLIENT);

        PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);
        OrderStatus currentStatus = order.getStatus();
        if (!canCancel(currentStatus)) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        order.setStatus(OrderStatus.CANCELLED);
        order.persist();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    private static boolean canCancel(OrderStatus currentStatus) {
        return Arrays.asList(
                OrderStatus.CREATED,
                OrderStatus.APPROVED,
                OrderStatus.REJECTED).contains(currentStatus);
    }

    @RequestMapping(value = "pos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PurchaseOrderResource> modifyOrder(@PathVariable Long id, @RequestBody PurchaseOrderResource res) {
        RBAC.assertAuthority(RBAC.ROLE_CLIENT);

        // TODO: check if plant is available in requested time range
        // TODO: check if other properties were modified and throw exception if they were

        PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);
        order.setSiteAddress(res.getSiteAddress());
        order.setStartDate(res.getStartDate());
        order.setEndDate(res.getEndDate());
        order.setTotal(res.getTotal());
        order.persist();

        PurchaseOrderResource resources = assembler.toResource(order);
        return new ResponseEntity<PurchaseOrderResource>(resources, HttpStatus.OK);
    }

}
