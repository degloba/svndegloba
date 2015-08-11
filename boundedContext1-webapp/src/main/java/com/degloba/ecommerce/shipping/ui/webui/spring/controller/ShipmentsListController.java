package com.degloba.ecommerce.shipping.ui.webui.spring.controller;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import command.Gate;


//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.shipping.api.commands.DeliverShipmentCommand;
import com.degloba.ecommerce.shipping.api.commands.SendShipmentCommand;
import com.degloba.ecommerce.shipping.readmodel.ShipmentDto;
import com.degloba.ecommerce.shipping.readmodel.ShipmentFinder;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Controller
@RequestMapping("/shipping/shipment")
public class ShipmentsListController {

    @Inject
    private ShipmentFinder finder;

    @Inject
    private Gate gate;

    @RequestMapping("/list")
    public String shippingList(Model model) {
        List<ShipmentDto> shipments = finder.findShipment();
        model.addAttribute("shipments", shipments);
        return "/shipping/shipmentsList";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String shipOrder(@RequestParam("shipmentId") String shipmentId) {
    	Key aggregateId = KeyFactory.stringToKey( shipmentId);
        gate.dispatch(new SendShipmentCommand(aggregateId));
        return "redirect:/shipping/shipment/list";
    }

    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    public String receiveShipment(@RequestParam("shipmentId") String shipmentId) {
    	Key aggregateId = KeyFactory.stringToKey( shipmentId);
        gate.dispatch(new DeliverShipmentCommand(aggregateId));
        return "redirect:/shipping/shipment/list";
    }
}
