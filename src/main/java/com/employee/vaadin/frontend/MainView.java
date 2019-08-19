package com.employee.vaadin.frontend;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.employee.vaadin.dao.EmployeeRepo;
import com.employee.vaadin.dao.TeamRepo;
import com.employee.vaadin.model.Employee;
import com.employee.vaadin.model.Team;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeRepo employeeRepo;
	private TeamRepo teamRepo;

	private final EmployeeCRUDUI employeeCrud;
	private final TeamCRUDUI teamCrud;
	private final CustomTeamGrid customTeamGrid;
	private final CustomEmployeeGrid customEmployeeGrid;

	public MainView(EmployeeRepo employeeRepo, TeamRepo teamRepo, EmployeeCRUDUI employeeCrud, TeamCRUDUI teamCrud) {
		this.employeeRepo = employeeRepo;
		this.teamRepo = teamRepo;
		this.employeeCrud = employeeCrud;
		this.teamCrud = teamCrud;

		// Instantiate new Grid that has customization
		customTeamGrid = new CustomTeamGrid(teamRepo);
		customEmployeeGrid = new CustomEmployeeGrid(employeeRepo);

		// Click event listener for Employee Grid
		customEmployeeGrid.employeeGrid.asSingleSelect().addValueChangeListener(e -> {
			employeeCrud.editEmployee(e.getValue());
			if (e.getValue() != null) {
				employeeCrud.delete.setEnabled(true);
				employeeCrud.edit.setEnabled(true);
			} else {
				employeeCrud.delete.setEnabled(false);
				employeeCrud.edit.setEnabled(false);
				employeeCrud.bottomWrapper.setVisible(false);
			}
		});

		// Re-fetch Employee data when save() or delete() is called
		employeeCrud.setChangeHandler(() -> {
			employeeCrud.setVisible(false);
			customEmployeeGrid.dataProvider = new ListDataProvider<>(employeeRepo.findAll());
			customEmployeeGrid.employeeGrid.setDataProvider(customEmployeeGrid.dataProvider);
		});

		// Click event listener for Team Grid
		customTeamGrid.teamGrid.asSingleSelect().addValueChangeListener(e -> {
			teamCrud.editTeam(e.getValue());
			if (e.getValue() != null) {
				teamCrud.delete.setEnabled(true);
				teamCrud.edit.setEnabled(true);
			} else {
				teamCrud.delete.setEnabled(false);
				teamCrud.edit.setEnabled(false);
				teamCrud.bottomWrapper.setVisible(false);
			}
		});

		// Re-fetch Team data when save() or delete() is called
		teamCrud.setChangeHandler(() -> {
			teamCrud.setVisible(false);
			customTeamGrid.teamDataProvider = new ListDataProvider<>(teamRepo.findAll());
			customTeamGrid.teamGrid.setDataProvider(customTeamGrid.teamDataProvider);
		});

		// Creating wrappers 
		VerticalLayout teamLayout = new VerticalLayout();
		VerticalLayout employeeLayout = new VerticalLayout();

		// Adding individual components to sub wrappers class
		teamLayout.add(teamCrud.topWrapper, customTeamGrid, teamCrud.bottomWrapper);
		employeeLayout.add(employeeCrud.topWrapper, customEmployeeGrid, employeeCrud.bottomWrapper);

		// Adding to main class
		add(teamLayout, employeeLayout);

		// Fetching data
		listEmployees();
		listTeam();
	}

	private void listEmployees() {
		customEmployeeGrid.dataProvider = new ListDataProvider<>(employeeRepo.findAll());
		customEmployeeGrid.employeeGrid.setDataProvider(customEmployeeGrid.dataProvider);
	}

	private void listTeam() {
		customTeamGrid.teamDataProvider = new ListDataProvider<>(teamRepo.findAll());
		customTeamGrid.teamGrid.setDataProvider(customTeamGrid.teamDataProvider);
	}
}