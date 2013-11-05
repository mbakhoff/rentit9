package esi.rentit9.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import esi.rentit9.domain.OrderStatus;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.interop.BuilditInterop;
import esi.rentit9.interop.Team9Rest;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/purchaseorders")
@Controller
@RooWebScaffold(path = "purchaseorders", formBackingObject = PurchaseOrder.class)
public class PurchaseOrderController {

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid PurchaseOrder purchaseOrder,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, purchaseOrder);
			return "purchaseorders/update";
		}

		PurchaseOrder old = PurchaseOrder.findPurchaseOrder(purchaseOrder
				.getId());
		if (old.getStatus() != OrderStatus.APPROVED
				&& purchaseOrder.getStatus() == OrderStatus.APPROVED) {
			BuilditInterop provider = purchaseOrder.getBuildit().getProvider();
			provider.getRest().sendAccept(purchaseOrder);
			// accept();
		} else if (old.getStatus() != OrderStatus.REJECTED
				&& purchaseOrder.getStatus() == OrderStatus.REJECTED) {
			BuilditInterop provider = purchaseOrder.getBuildit().getProvider();
			provider.getRest().sendReject(purchaseOrder);
		}

		uiModel.asMap().clear();
		purchaseOrder.merge();
		return "redirect:/purchaseorders/"
				+ encodeUrlPathSegment(purchaseOrder.getId().toString(),
						httpServletRequest);
	}
}
