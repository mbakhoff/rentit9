// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.web;

import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.domain.PurchaseOrderLine;
import esi.rentit9.web.PurchaseOrderLineController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect PurchaseOrderLineController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String PurchaseOrderLineController.create(@Valid PurchaseOrderLine purchaseOrderLine, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseOrderLine);
            return "purchaseorderlines/create";
        }
        uiModel.asMap().clear();
        purchaseOrderLine.persist();
        return "redirect:/purchaseorderlines/" + encodeUrlPathSegment(purchaseOrderLine.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String PurchaseOrderLineController.createForm(Model uiModel) {
        populateEditForm(uiModel, new PurchaseOrderLine());
        return "purchaseorderlines/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String PurchaseOrderLineController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("purchaseorderline", PurchaseOrderLine.findPurchaseOrderLine(id));
        uiModel.addAttribute("itemId", id);
        return "purchaseorderlines/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String PurchaseOrderLineController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("purchaseorderlines", PurchaseOrderLine.findPurchaseOrderLineEntries(firstResult, sizeNo));
            float nrOfPages = (float) PurchaseOrderLine.countPurchaseOrderLines() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("purchaseorderlines", PurchaseOrderLine.findAllPurchaseOrderLines());
        }
        addDateTimeFormatPatterns(uiModel);
        return "purchaseorderlines/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String PurchaseOrderLineController.update(@Valid PurchaseOrderLine purchaseOrderLine, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseOrderLine);
            return "purchaseorderlines/update";
        }
        uiModel.asMap().clear();
        purchaseOrderLine.merge();
        return "redirect:/purchaseorderlines/" + encodeUrlPathSegment(purchaseOrderLine.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String PurchaseOrderLineController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, PurchaseOrderLine.findPurchaseOrderLine(id));
        return "purchaseorderlines/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String PurchaseOrderLineController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PurchaseOrderLine purchaseOrderLine = PurchaseOrderLine.findPurchaseOrderLine(id);
        purchaseOrderLine.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/purchaseorderlines";
    }
    
    void PurchaseOrderLineController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("purchaseOrderLine_startdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("purchaseOrderLine_enddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void PurchaseOrderLineController.populateEditForm(Model uiModel, PurchaseOrderLine purchaseOrderLine) {
        uiModel.addAttribute("purchaseOrderLine", purchaseOrderLine);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("plants", Plant.findAllPlants());
        uiModel.addAttribute("purchaseorders", PurchaseOrder.findAllPurchaseOrders());
    }
    
    String PurchaseOrderLineController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
