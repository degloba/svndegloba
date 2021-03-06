package com.degloba.domain.entity;

public class Dept extends Organization {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int level;

	public Dept(String name, int level) {
		super(name);
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String[] businessKeys() {
		return new String[] { "name", "level" };
	}

	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}

}