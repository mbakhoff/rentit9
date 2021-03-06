// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.web;

import esi.rentit9.domain.BuildIt;
import esi.rentit9.interop.InteropImplementation;
import esi.rentit9.web.BuildItController;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect BuildItController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String BuildItController.create(@Valid BuildIt buildIt, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, buildIt);
            return "buildits/create";
        }
        uiModel.asMap().clear();
        buildIt.persist();
        return "redirect:/buildits/" + encodeUrlPathSegment(buildIt.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String BuildItController.createForm(Model uiModel) {
        populateEditForm(uiModel, new BuildIt());
        return "buildits/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String BuildItController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("buildit", BuildIt.findBuildIt(id));
        uiModel.addAttribute("itemId", id);
        return "buildits/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String BuildItController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("buildits", BuildIt.findBuildItEntries(firstResult, sizeNo));
            float nrOfPages = (float) BuildIt.countBuildIts() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("buildits", BuildIt.findAllBuildIts());
        }
        return "buildits/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String BuildItController.update(@Valid BuildIt buildIt, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, buildIt);
            return "buildits/update";
        }
        uiModel.asMap().clear();
        buildIt.merge();
        return "redirect:/buildits/" + encodeUrlPathSegment(buildIt.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String BuildItController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, BuildIt.findBuildIt(id));
        return "buildits/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String BuildItController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        BuildIt buildIt = BuildIt.findBuildIt(id);
        buildIt.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/buildits";
    }
    
    void BuildItController.populateEditForm(Model uiModel, BuildIt buildIt) {
        uiModel.addAttribute("buildIt", buildIt);
        uiModel.addAttribute("interopimplementations", Arrays.asList(InteropImplementation.values()));
    }
    
    String BuildItController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
