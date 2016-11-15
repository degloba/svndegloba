package com.degloba.ecommerce.shipping.ui.webui.spring.controller;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

// Spring
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// CQRS
import com.degloba.cqrs.command.Gate;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
// Ecommerce


import com.degloba.ecommerce.shipping.application.commands.DeliverShipmentCommand;
import com.degloba.ecommerce.shipping.application.commands.SendShipmentCommand;
import com.degloba.ecommerce.shipping.cqrs.readmodel.dtos.ShipmentDto;
import com.degloba.ecommerce.shipping.cqrs.readmodel.finders.IShipmentFinder;


@Controller
@RequestMapping("/shipping/shipment")
public class ShipmentsListController {

    @Inject
    private IShipmentFinder shipmentFinder;

    @Inject
    private Gate gate;

    @RequestMapping("/list")
    public String shippingList(Model model) {
        List<ShipmentDto> shipments = shipmentFinder.findShipment();
        model.addAttribute("shipments", shipments);
        return "/shipping/shipmentsList";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String shipOrder(@RequestParam("shipmentId") String shipmentId) {
    	/////Key aggregateId = KeyFactory.stringToKey( shipmentId);
    	AggregateId aggregateId = AggregateId.generate();   //Long.parseLong(shipmentId);
        gate.dispatch(new SendShipmentCommand(aggregateId));
        return "redirect:/shipping/shipment/list";
    }

    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    public String receiveShipment(@RequestParam("shipmentId") String shipmentId) {
    	////////Key aggregateId = KeyFactory.stringToKey( shipmentId);
    	AggregateId aggregateId = AggregateId.generate();  //Long.parseLong(shipmentId);
        gate.dispatch(new DeliverShipmentCommand(aggregateId));
        return "redirect:/shipping/shipment/list";
    }
}
