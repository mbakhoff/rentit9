package esi.rentit9.web;

import esi.rentit9.domain.OrderStatus;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.interop.BuilditInterop;
import esi.rentit9.service.Invoicing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/purchaseorders")
@Controller
@RooWebScaffold(path = "purchaseorders", formBackingObject = PurchaseOrder.class)
public class PurchaseOrderController {

    @Autowired
    private JavaMailSender smtp;

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid PurchaseOrder purchaseOrder,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, purchaseOrder);
			return "purchaseorders/update";
		}

		PurchaseOrder old = PurchaseOrder.findPurchaseOrder(purchaseOrder.getId());
        boolean statusChanged = old.getStatus() != purchaseOrder.getStatus();

		if (statusChanged && purchaseOrder.getStatus() == OrderStatus.APPROVED) {
			BuilditInterop provider = purchaseOrder.getBuildit().getInterop();
			provider.sendAccept(purchaseOrder);
		}
        if (statusChanged && purchaseOrder.getStatus() == OrderStatus.REJECTED) {
			BuilditInterop provider = purchaseOrder.getBuildit().getInterop();
			provider.sendReject(purchaseOrder);
		}
        if (statusChanged && purchaseOrder.getStatus() == OrderStatus.PLANT_RETURNED) {
            Invoicing.sendInvoice(smtp, Invoicing.createInvoice(purchaseOrder));
            purchaseOrder.setStatus(OrderStatus.INVOICED);
        }

		uiModel.asMap().clear();
		purchaseOrder.merge();
		return "redirect:/purchaseorders/"
				+ encodeUrlPathSegment(purchaseOrder.getId().toString(),
						httpServletRequest);
	}
}
