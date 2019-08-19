package com.employee.vaadin.model;

public enum ActiveStatus {
	INACTIVE("0"), ACTIVE("1");
	private final String value;

	/*
	 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html
	 *
	 * Instantiates a new sex.
	 *
	 * @param value the value
	 */
	private ActiveStatus(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
