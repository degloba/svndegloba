package com.degloba.viatges.webapp.controllers.impl.spring;


import java.security.Principal;
import java.util.List;

import javax.inject.Inject;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.degloba.viatges.application.SearchCriteria;
import com.degloba.viatges.application.services.IViatgesService;
import com.degloba.viatges.domainpersistence.rdbms.jpa.Hotel;
import com.degloba.viatges.domainpersistence.rdbms.jpa.Reserva;


/**
 * 
 * @author degloba
 * 
 * Controlador web implementat amb Spring que dóna funcionalitat sobre {@link Hotel}s i {@link Reserva}s
 *
 */
@Controller
public class HotelsController {

 	@Inject 
 	private IViatgesService reservaService;

	@RequestMapping(value = "/hotels/search", method = RequestMethod.GET)
	public void search(SearchCriteria searchCriteria, Principal currentUser, Model model) {
		if (currentUser != null) {
			List<Reserva> reserva = reservaService.buscarReserves(currentUser.getName());
			model.addAttribute(reserva);
		}
	}

	@RequestMapping(value = "/hotels", method = RequestMethod.GET)
	public String list(SearchCriteria criteria, Model model) {
		List<Hotel> hotels = reservaService.buscarHotels(criteria);
		model.addAttribute(hotels);
		return "hotels/list";
	}

	@RequestMapping(value = "/hotels/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute(reservaService.buscarHotelById(id));
		return "hotels/show";
	}

	@RequestMapping(value = "/reserves/{id}", method = RequestMethod.DELETE)
	public String deleteReserva(@PathVariable Long id) {
		reservaService.cancelaReserva(id);
		return "redirect:../hotels/search";
	}

}
