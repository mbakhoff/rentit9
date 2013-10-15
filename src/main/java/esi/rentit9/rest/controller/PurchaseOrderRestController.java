package esi.rentit9.rest.controller;

import esi.rentit9.domain.BuildIt;
import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.domain.PurchaseOrderLine;
import esi.rentit9.rest.PurchaseOrderLineResource;
import esi.rentit9.rest.PurchaseOrderResource;
import esi.rentit9.rest.PurchaseOrderResourceAssembler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

	private void attachLines(PurchaseOrder order, List<PurchaseOrderLineResource> purchaseOrderLines) {
		for (PurchaseOrderLineResource res : purchaseOrderLines) {
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
		List<BuildIt> buildIts = BuildIt.findAllBuildIts();
		BuildIt match = null;
		for (BuildIt buildIt : buildIts) {
			if (buildIt.getUrl().equals(incomingUrl)) {
				match = buildIt;
				break;
			}
		}
		if (match == null) {
			match = new BuildIt();
			match.setUrl(incomingUrl);
			match.persist();
		}
		return match;
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
