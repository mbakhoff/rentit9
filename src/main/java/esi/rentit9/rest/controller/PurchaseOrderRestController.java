package esi.rentit9.rest.controller;

import esi.rentit9.domain.*;
import esi.rentit9.rest.PurchaseOrderLineResource;
import esi.rentit9.rest.PurchaseOrderLineResourceList;
import esi.rentit9.rest.PurchaseOrderResource;
import esi.rentit9.rest.PurchaseOrderResourceAssembler;
import esi.rentit9.rest.util.MethodLookup;
import esi.rentit9.rest.util.MethodLookupHelper;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/rest/")
public class PurchaseOrderRestController {

    private static final int METHOD_GET_PO_BY_ID = 1;

    private final PurchaseOrderResourceAssembler assembler;
    private final MethodLookupHelper linker;

	public PurchaseOrderRestController() {
		assembler = new PurchaseOrderResourceAssembler();
        linker = new MethodLookupHelper(PurchaseOrderRestController.class);
    }

	@RequestMapping("pos")
	public ResponseEntity<PurchaseOrderResourceList> getAll() {
		List<PurchaseOrder> orders = PurchaseOrder.findAllPurchaseOrders();
		List<PurchaseOrderResource> resources = assembler.toResource(orders);
		return new ResponseEntity<PurchaseOrderResourceList>(
				new PurchaseOrderResourceList(resources), HttpStatus.OK);
	}

	@RequestMapping(value = "pos", method = RequestMethod.POST)
	public ResponseEntity<Void> createOrder(@RequestBody PurchaseOrderResource res) {
		PurchaseOrder order = new PurchaseOrder();
		order.setBuildit(getOrCreateBuildIt(res.buildit));
		order.setSiteAddress(res.siteAddress);
		order.setStatus(OrderStatus.Created);
		order.persist();

		attachLines(order, res.purchaseOrderLines);

		HttpHeaders headers = new HttpHeaders();
		URI location =
				ServletUriComponentsBuilder.fromCurrentRequestUri().
						pathSegment(order.getId().toString()).build().toUri();
		headers.setLocation(location);
		headers.add("RentItId", (order.getId()).toString());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping("po/{id}")
    @MethodLookup(METHOD_GET_PO_BY_ID)
	public ResponseEntity<PurchaseOrderResource> getById(@PathVariable Long id) {
		PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);
		PurchaseOrderResource resource = assembler.toResource(order);
        resource.add(linker.buildLink(METHOD_GET_PO_BY_ID, order.getId()));
		return new ResponseEntity<PurchaseOrderResource>(resource, HttpStatus.OK);
	}
	
	@RequestMapping(value = "po/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);
		order.setStatus(OrderStatus.Cancelled);
		order.persist();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "po/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> modifyOrder(@PathVariable Long id, @RequestBody PurchaseOrderResource res) {
		PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);
		order.setBuildit(getOrCreateBuildIt(res.buildit));
		order.setSiteAddress(res.siteAddress);
		order.persist();

		deleteLines(order);

		attachLines(order, res.purchaseOrderLines);

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

	private void attachLines(PurchaseOrder order, PurchaseOrderLineResourceList purchaseOrderLines) {
		for (PurchaseOrderLineResource res : purchaseOrderLines.purchaseOrders) {
			attachLine(order, res);
		}
	}

	private void attachLine(PurchaseOrder order, PurchaseOrderLineResource res) {
		PurchaseOrderLine line = new PurchaseOrderLine();
		line.setPlant(getPlant(res.plantId));
		line.setStartDate(res.startDate);
		line.setEndDate(res.endDate);
		line.setTotal(res.totalPrice); // TODO: recalculate
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
