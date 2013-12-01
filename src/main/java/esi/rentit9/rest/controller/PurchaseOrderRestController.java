package esi.rentit9.rest.controller;

import esi.rentit9.RBAC;
import esi.rentit9.domain.*;
import esi.rentit9.rest.PurchaseOrderLineListResource;
import esi.rentit9.rest.PurchaseOrderLineResource;
import esi.rentit9.rest.PurchaseOrderResource;
import esi.rentit9.rest.PurchaseOrderResourceAssembler;
import esi.rentit9.rest.util.HttpHelpers;
import esi.rentit9.rest.util.MethodLookup;
import esi.rentit9.rest.util.MethodLookupHelper;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/rest/")
public class PurchaseOrderRestController {

    public static final int METHOD_GET_ALL = 1;
    public static final int METHOD_CREATE_ORDER = 2;
    public static final int METHOD_GET_BY_ID = 3;
    public static final int METHOD_DELETE_BY_ID = 4;
    public static final int METHOD_MODIFY_ORDER = 5;

    private final PurchaseOrderResourceAssembler assembler;
    private final MethodLookupHelper linker;

	public PurchaseOrderRestController() {
		assembler = new PurchaseOrderResourceAssembler();
        linker = new MethodLookupHelper(PurchaseOrderRestController.class);
	}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ex.printStackTrace();
        if (ex instanceof RBAC.UnauthorizedAccessException) {
            return new ResponseEntity<String>(HttpHelpers.getStack(ex), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<String>(HttpHelpers.getStack(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@RequestMapping("pos")
    @MethodLookup(METHOD_GET_ALL)
	public ResponseEntity<PurchaseOrderResourceList> getAll() {
		List<PurchaseOrder> orders = PurchaseOrder.findAllPurchaseOrders();
		List<PurchaseOrderResource> resources = assembler.toResource(orders);
		return new ResponseEntity<PurchaseOrderResourceList>(
				new PurchaseOrderResourceList(resources), HttpStatus.OK);
	}

	@RequestMapping(value = "pos", method = RequestMethod.POST)
    @MethodLookup(METHOD_CREATE_ORDER)
	public ResponseEntity<Void> createOrder(@RequestBody PurchaseOrderResource res) {
        RBAC.assertAuthority(RBAC.ROLE_CLIENT);

		PurchaseOrder order = new PurchaseOrder();
		order.setBuildit(getOrCreateBuildIt(res.getBuildit()));
		order.setSiteAddress(res.getSiteAddress());
		order.setStatus(OrderStatus.CREATED);
		order.persist();

		attachLines(order, res.getPurchaseOrderLines());

		HttpHeaders headers = new HttpHeaders();
		URI location =
				ServletUriComponentsBuilder.fromCurrentRequestUri().
						pathSegment(order.getId().toString()).build().toUri();
		headers.setLocation(location);
		headers.add("RentItId", (order.getId()).toString());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping("pos/{id}")
    @MethodLookup(METHOD_GET_BY_ID)
	public ResponseEntity<PurchaseOrderResource> getById(@PathVariable Long id) {
		PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);
		PurchaseOrderResource resources = assembler.toResource(order);
        resources.add(linker.buildLink(METHOD_DELETE_BY_ID, order.getId()));
        resources.add(linker.buildLink(METHOD_MODIFY_ORDER, order.getId()));
		return new ResponseEntity<PurchaseOrderResource>(resources, HttpStatus.OK);
	}
	
	@RequestMapping(value = "pos/{id}", method = RequestMethod.DELETE)
    @MethodLookup(METHOD_DELETE_BY_ID)
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        RBAC.assertAuthority(RBAC.ROLE_ADMIN);

		PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);
		OrderStatus currentStatus = order.getStatus();
		if (currentStatus == OrderStatus.CREATED || 
				currentStatus == OrderStatus.APPROVED || 
				currentStatus == OrderStatus.REJECTED){
			order.setStatus(OrderStatus.CANCELLED);
			order.persist();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "pos/{id}", method = RequestMethod.PUT)
    @MethodLookup(METHOD_MODIFY_ORDER)
	public ResponseEntity<Void> modifyOrder(@PathVariable Long id, @RequestBody PurchaseOrderResource res) {
        RBAC.assertAuthority(RBAC.ROLE_CLIENT);

		PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);
		order.setBuildit(getOrCreateBuildIt(res.getBuildit()));
		order.setSiteAddress(res.getSiteAddress());
		order.persist();

		deleteLines(order);

		attachLines(order, res.getPurchaseOrderLines());

		HttpHeaders headers = new HttpHeaders();
		URI location =
				ServletUriComponentsBuilder.fromCurrentRequestUri().
						pathSegment(order.getId().toString()).build().toUri();
		headers.setLocation(location);
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	private void deleteLines(PurchaseOrder order) {
		for (PurchaseOrderLine line : order.getLines()){
			line.remove();
		}
	}

	private void attachLines(PurchaseOrder order, PurchaseOrderLineListResource purchaseOrderLines) {
		for (PurchaseOrderLineResource res : purchaseOrderLines.purchaseOrders) {
			attachLine(order, res);
		}
	}

	private void attachLine(PurchaseOrder order, PurchaseOrderLineResource res) {
		PurchaseOrderLine line = new PurchaseOrderLine();
		line.setPlant(getPlant(res.getPlantId()));
		line.setStartDate(res.getStartDate());
		line.setEndDate(res.getEndDate());
		line.setTotal(res.getTotalPrice()); // TODO: recalculate
		line.setPurchaseOrder(order);
		line.persist();
	}

	private Plant getPlant(String plantId) {
		return Plant.findPlant(Long.parseLong(plantId));
	}

	private BuildIt getOrCreateBuildIt(String incomingUrl) {
		try {
			return BuildIt.getByUrl(incomingUrl);
		} catch (DataRetrievalFailureException notFound) {
			BuildIt match = new BuildIt();
			match.setUrl(incomingUrl);
			match.persist();
			return match;
		}
	}

	@XmlRootElement(name = "purchaseorders")
	public static class PurchaseOrderResourceList {
		public List<PurchaseOrderResource> purchaseorder;

		@SuppressWarnings("UnusedDeclaration")
		public PurchaseOrderResourceList() {
		}

		public PurchaseOrderResourceList(List<PurchaseOrderResource> purchaseorder) {
			this.purchaseorder = purchaseorder;
		}
	}
}
