package com.insacosa.webui;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import command.Gate;
import com.insacosa.application.commands.SubmitOrderCommand;
import com.insacosa.presentation.OrderFinder;

@Controller
@RequestMapping("/sales/confirmOrder")
public class OrderConfirmationController {

    @Inject
    private Gate gate;

    @Inject
    private OrderFinder orderFinder;

    @RequestMapping("/{orderId}")
    public String orderConfirmation(@PathVariable("orderId") Long orderId, Model model) {
        model.addAttribute("order", orderFinder.getClientOrderDetails(orderId));
        return "sales/orderConfirmation";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitOrder(@RequestParam("orderId") Long orderId) {
        gate.dispatch(new SubmitOrderCommand(orderId));
        return "redirect:/sales/confirmOrder/" + orderId;
    }
}
