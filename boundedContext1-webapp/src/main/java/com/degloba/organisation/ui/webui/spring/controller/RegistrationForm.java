package com.degloba.organisation.ui.webui.spring.controller;

import com.degloba.security.spring.gae.validation.Forename;
import com.degloba.security.spring.gae.validation.Surname;

/**
 * @author Luke Taylor
 */
public class RegistrationForm {
	@Forename()
	private String forename;
	@Surname()
	private String surname;

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
