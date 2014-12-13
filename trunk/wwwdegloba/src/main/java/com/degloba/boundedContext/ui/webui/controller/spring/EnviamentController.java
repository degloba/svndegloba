package com.degloba.boundedContext.ui.webui.controller.spring;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.degloba.boundedContext.enviaments.readmodel.EnviamentDto;
import com.degloba.boundedContext.enviaments.readmodel.IEnviamentFinder;

import command.IGate;
import domain.canonicalmodel.publishedlanguage.AggregateId;


@Controller
@RequestMapping("/shipping/shipment")
public class EnviamentController {


    @Inject
    private IEnviamentFinder finder;

    @Inject
    private IGate gate;

    @RequestMapping("/list")
    public String shippingList(Model model) {
        List<EnviamentDto> shipments = finder.findShipment();
        model.addAttribute("shipments", shipments);
        return "/shipping/shipmentsList";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String shipOrder(@RequestParam("shipmentId") String shipmentId) {
        gate.dispatch(new SendShipmentCommand(new AggregateId(shipmentId)));
        return "redirect:/shipping/shipment/list";
    }

    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    public String receiveShipment(@RequestParam("shipmentId") String shipmentId) {
        gate.dispatch(new DeliverShipmentCommand(new AggregateId(shipmentId)));
        return "redirect:/shipping/shipment/list";
    }
}

