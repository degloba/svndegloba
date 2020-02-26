package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.degloba.cqrs.command.Gate;
import com.degloba.ecommerce.enviaments.cqrs.queries.finders.IEnviamentFinder;
import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;


/**
 * @category es un {@link Controller} que gestiona els {@link Enviament}s
 * 
 * @author degloba
 *
 */
@Controller
@RequestMapping("/shipping/shipment")
public class EnviamentsController {

    @Inject
    private IEnviamentFinder enviamentFinder;

    @Inject
    private Gate gate;

    @RequestMapping("/list")
    public String shippingList(Model model) {
        List<EnviamentDto> shipments = enviamentFinder.buscaEnviaments();
        model.addAttribute("shipments", shipments);
        return "/shipping/shipmentsList";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String shipOrder(@RequestParam("enviamentId") String enviamentId) {
    	/////Key aggregateId = KeyFactory.stringToKey( enviamentId);
    	//AggregateId aggregateId = AggregateId.generate();   //Long.parseLong(enviamentId);
        //gate.dispatch(new SendShipmentCommand(aggregateId));
        return "redirect:/shipping/shipment/list";
    }

    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    public String receiveShipment(@RequestParam("enviamentId") String enviamentId) {
    	////////Key aggregateId = KeyFactory.stringToKey( enviamentId);
    	//AggregateId aggregateId = AggregateId.generate();  //Long.parseLong(enviamentId);
        //gate.dispatch(new DeliverShipmentCommand(aggregateId));
        return "redirect:/shipping/shipment/list";
    }
}
