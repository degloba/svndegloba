package com.degloba.viatges.webapp.controllers.rest.impl.spring;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/*import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;*/

import com.degloba.viatges.domainpersistence.rdbms.jpa.Reserves;
import com.degloba.viatges.webapp.services.ClientReservesService;

/**
 * @category punt d'entrada dels clients http (browser)
 * 
 * @author degloba
 *
 */
@Controller
public class ReservaClientController {
	
  @Autowired
  ClientReservesService clientReservesService;
  
  @RequestMapping("/lista")
  public String lista(final Model modelo) {
    
    List<Reserves> lista = clientReservesService.buscarTotesReserves().collectList().block();
    
    modelo.addAttribute("lista", lista);
    
    return "lista";
  }
}
