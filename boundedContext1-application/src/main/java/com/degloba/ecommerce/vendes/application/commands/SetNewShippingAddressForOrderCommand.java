package com.degloba.ecommerce.vendes.application.commands;


import com.degloba.cqrs.command.annotations.Command;

@Command
public class SetNewShippingAddressForOrderCommand {
	private Long comandaId;
	private String fullName;
	private String streetAddress;
	private String city;
	private String region;
	private String postalCode;
	private String country;
	private String phoneNumber;

	public Long getComandaId() {
		return comandaId;
	}

	public void setOrderId(Long comandaId) {
		this.comandaId = comandaId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
