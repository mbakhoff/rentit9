package esi.rentit9.web.users;
import esi.rentit9.domain.PurchaseOrder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/users/po")
@Controller
@RooWebScaffold(path = "users/po", formBackingObject = PurchaseOrder.class, delete = false, create = false)
public class POUController {


    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String update(@Valid PurchaseOrder purchaseOrder,
                                       BindingResult bindingResult, Model uiModel,
                                       HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseOrder);
            return "users/po/update";
        }
        uiModel.asMap().clear();
        purchaseOrder.merge();
        return "redirect:/users/po/" + encodeUrlPathSegment(purchaseOrder.getId().toString(), httpServletRequest);
    }
}
