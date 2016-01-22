package com.degloba.organisation.ui.webui.spring.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Luke Taylor
 *
 */
@Controller
public class GaeAppController {

	@RequestMapping(value = "/gae", method = RequestMethod.GET)
	public String landing() {
		return "gae/landing";
	}

	@RequestMapping(value = "/gae/home.htm", method = RequestMethod.GET)
	public String home() {
		return "gae/home";
	}

	@RequestMapping(value = "/gae/disabled.htm", method = RequestMethod.GET)
	public String disabled() {
		return "gae/disabled";
	}

	@RequestMapping(value = "/gae/logout.htm", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();

		String logoutUrl = UserServiceFactory.getUserService().createLogoutURL(
				"/gae/loggedout.htm");

		response.sendRedirect(logoutUrl);
	}

	@RequestMapping(value = "/gae/loggedout.htm", method = RequestMethod.GET)
	public String loggedOut() {
		return "gae/loggedout";
	}
}
