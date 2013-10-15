package esi.rentit9.rest.controller;

import esi.rentit9.domain.BuildIt;
import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.domain.PurchaseOrderLine;
import esi.rentit9.rest.PurchaseOrderLineResource;
import esi.rentit9.rest.PurchaseOrderLineResourceList;
import esi.rentit9.rest.PurchaseOrderResource;
import esi.rentit9.rest.PurchaseOrderResourceAssembler;
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
@RequestMapping("/rest")
public class PurchaseOrderRestController {

	private PurchaseOrderResourceAssembler assembler;

	public PurchaseOrderRestController() {
		assembler = new PurchaseOrderResourceAssembler();
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
		order.setBuildit(getOrCreateBuildIt(res.getBuildit()));
		order.setSiteAddress(res.getSiteAddress());
		order.persist();

		attachLines(order, res.getPurchaseOrderLines());

		HttpHeaders headers = new HttpHeaders();
		URI location =
				ServletUriComponentsBuilder.fromCurrentRequestUri().
						pathSegment(order.getId().toString()).build().toUri();
		headers.setLocation(location);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping("po/{id}")
	public ResponseEntity<PurchaseOrderResource> getById(@PathVariable Long id) {
		PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);
		PurchaseOrderResource resources = assembler.toResource(order);
		return new ResponseEntity<PurchaseOrderResource>(resources, HttpStatus.OK);
	}
	
	@RequestMapping(value = "po/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		PurchaseOrder order = PurchaseOrder.findPurchaseOrder(id);
		deleteLines(order);
		order.remove();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "po/{id}/modify", method = RequestMethod.PUT)
	public ResponseEntity<Void> modifyOrder(@PathVariable Long id, @RequestBody PurchaseOrderResource res) {
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
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
