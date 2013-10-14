package esi.rentit9.rest.controller;

import esi.rentit9.rest.PlantResource;
import esi.rentit9.rest.PurchaseOrderResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: kmetsalu
 * Date: 10/14/13
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/rest")
public class PurchaseOrderRestController {

    @RequestMapping("createPO")
    public ResponseEntity<Void> createPO(@RequestBody PurchaseOrderResource res){
        //TODO: implement

        return null;
    }
}
