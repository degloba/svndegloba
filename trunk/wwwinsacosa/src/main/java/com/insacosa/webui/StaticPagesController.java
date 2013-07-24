package com.insacosa.webui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticPagesController {

    @RequestMapping("/")
    public String welcomePage() {
        return "redirect:/sales/products/list";
    }
}
