package esi.rentit9.web;
import esi.rentit9.domain.BuildIt;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/buildits")
@Controller
@RooWebScaffold(path = "buildits", formBackingObject = BuildIt.class)
public class BuildItController {
}
