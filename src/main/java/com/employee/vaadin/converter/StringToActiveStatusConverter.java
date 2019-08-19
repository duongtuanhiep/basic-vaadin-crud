package com.employee.vaadin.converter;

import com.employee.vaadin.model.ActiveStatus;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class StringToActiveStatusConverter implements Converter<String, ActiveStatus> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Result<ActiveStatus> convertToModel(String value, ValueContext context) {
		for (ActiveStatus status : ActiveStatus.values()) {
			if (status.getValue().equals(value)) {
				return Result.ok(status);
			}
		}
		throw new IllegalArgumentException("Unknown value " + value);
	}

	@Override
	public String convertToPresentation(ActiveStatus value, ValueContext context) {
		return value.getValue();
	}

	// public String convertToPresentation(ActiveStatus activeStatus) throws
	// ConversionException {
	// if (activeStatus.getValue() == null) {
	// return null;
	// } else {
	// return activeStatus.getValue();
	// }
	// }

	// public Class<Name> getModelType() {
	// return Name.class;
	// }
	//
	// public Class<String> getPresentationType() {
	// return String.class;
	// }
}