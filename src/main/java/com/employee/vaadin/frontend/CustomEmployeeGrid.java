package com.employee.vaadin.frontend;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.employee.vaadin.dao.EmployeeRepo;
import com.employee.vaadin.model.Employee;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class CustomEmployeeGrid extends VerticalLayout {

	private EmployeeRepo employeeRepo;
	ListDataProvider<Employee> dataProvider;
	Grid<Employee> employeeGrid;

	@Autowired
	public CustomEmployeeGrid(EmployeeRepo employeeRepo) {
		/*
		 * 
		 * EMPLOYEE GRID CONFIGURATION
		 * 
		 **/
		employeeGrid = new Grid<>();
		this.employeeRepo = employeeRepo;

		dataProvider = new ListDataProvider<>(employeeRepo.findAll());
		employeeGrid.setDataProvider(dataProvider);

		// Creating the columns for the grid
		employeeGrid.setColumnReorderingAllowed(true);
		Grid.Column<Employee> employeeId = employeeGrid.addColumn(Employee::getId).setHeader("Eye Dee");
		Grid.Column<Employee> employeeFirstName = employeeGrid.addColumn(Employee::getFirstName)
				.setHeader("First Name");
		Grid.Column<Employee> employeeLastName = employeeGrid.addColumn(Employee::getLastName).setHeader("Last Name");
		Grid.Column<Employee> employeeEmail = employeeGrid.addColumn(Employee::getEmailAddress).setHeader("Mail");
		Grid.Column<Employee> employeeActiveStatus = employeeGrid.addColumn(Employee::getActiveStatus)
				.setHeader("Active Status");
		Grid.Column<Employee> employeeTeamId = employeeGrid.addColumn(Employee::getTeamId).setHeader("Team Id");

		// Creating filter row in the Grid on top
		HeaderRow filterRow = employeeGrid.appendHeaderRow();

		// First column filter
		TextField idField = new TextField();
		idField.addValueChangeListener(event -> dataProvider
				.addFilter(employee -> StringUtils.containsIgnoreCase(employee.getId(), idField.getValue())));
		idField.setValueChangeMode(ValueChangeMode.EAGER);
		filterRow.getCell(employeeId).setComponent(idField);
		idField.setSizeFull();
		idField.setPlaceholder("ID");

		// Second column
		TextField firstNameField = new TextField();
		firstNameField.addValueChangeListener(event -> dataProvider.addFilter(
				employee -> StringUtils.containsIgnoreCase(employee.getFirstName(), firstNameField.getValue())));
		firstNameField.setValueChangeMode(ValueChangeMode.EAGER);
		filterRow.getCell(employeeFirstName).setComponent(firstNameField);
		firstNameField.setSizeFull();
		firstNameField.setPlaceholder("FIRST NAME");

		// Third column Filter
		TextField lastNameField = new TextField();
		lastNameField.addValueChangeListener(event -> dataProvider.addFilter(
				employee -> StringUtils.containsIgnoreCase(employee.getLastName(), lastNameField.getValue())));
		lastNameField.setValueChangeMode(ValueChangeMode.EAGER);
		filterRow.getCell(employeeLastName).setComponent(lastNameField);
		lastNameField.setSizeFull();
		lastNameField.setPlaceholder("LAST NAME");

		// Fourth column Filter
		TextField emailField = new TextField();
		emailField.addValueChangeListener(event -> dataProvider.addFilter(
				employee -> StringUtils.containsIgnoreCase(employee.getEmailAddress(), emailField.getValue())));
		emailField.setValueChangeMode(ValueChangeMode.EAGER);
		filterRow.getCell(employeeEmail).setComponent(emailField);
		emailField.setSizeFull();
		emailField.setPlaceholder("Email");

		// Fifth column Filter
		TextField activeStatusField = new TextField();
		activeStatusField.addValueChangeListener(event -> dataProvider.addFilter(employee -> StringUtils
				.containsIgnoreCase(employee.getActiveStatus().toString(), activeStatusField.getValue())));
		activeStatusField.setValueChangeMode(ValueChangeMode.EAGER);
		filterRow.getCell(employeeActiveStatus).setComponent(activeStatusField);
		activeStatusField.setSizeFull();
		activeStatusField.setPlaceholder("ACTIVE");

		// Sixth column Filter
		TextField teamIdField = new TextField();
		teamIdField.addValueChangeListener(event -> dataProvider.addFilter(employee -> StringUtils
				.containsIgnoreCase(employee.getActiveStatus().toString(), teamIdField.getValue())));
		teamIdField.setValueChangeMode(ValueChangeMode.EAGER);
		filterRow.getCell(employeeTeamId).setComponent(teamIdField);
		teamIdField.setSizeFull();
		teamIdField.setPlaceholder("TEAM ID");

		// Adding the component into the Layouts
		add(employeeGrid);
	}

	public ListDataProvider<Employee> getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(ListDataProvider<Employee> dataProvider) {
		this.dataProvider = dataProvider;
	}

	public Grid<Employee> getEmployeeGrid() {
		return employeeGrid;
	}

	public void setEmployeeGrid(Grid<Employee> employeeGrid) {
		this.employeeGrid = employeeGrid;
	}

}
