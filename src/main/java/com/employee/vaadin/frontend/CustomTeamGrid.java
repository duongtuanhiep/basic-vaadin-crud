package com.employee.vaadin.frontend;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.employee.vaadin.dao.TeamRepo;
import com.employee.vaadin.model.Team;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;

public class CustomTeamGrid extends VerticalLayout {

	private TeamRepo teamRepo;
	ListDataProvider<Team> teamDataProvider;
	Grid<Team> teamGrid;

	@Autowired
	public CustomTeamGrid(TeamRepo teamRepo) {

		/*
		 * 
		 * TEAM GRID CONFIGURATION
		 * 
		 **/
		this.teamGrid = new Grid<>();
		this.teamRepo = teamRepo;

		// Creating dataprovider for the grid
		teamDataProvider = new ListDataProvider<>(teamRepo.findAll());
		teamGrid.setDataProvider(teamDataProvider);

		// Configuring the columns for the grid
		teamGrid.setColumnReorderingAllowed(true);
		Grid.Column<Team> id = teamGrid.addColumn(Team::getId).setHeader("Eye Dee");
		Grid.Column<Team> name = teamGrid.addColumn(Team::getName).setHeader("NAME");
		Grid.Column<Team> managedBy = teamGrid.addColumn(Team::getManagedBy).setHeader("MANAGED BY");

		// Creating filter row in the Grid on top
		HeaderRow teamFilterRow = teamGrid.appendHeaderRow();

		// First column filter
		TextField teamIDField = new TextField();
		teamIDField.addValueChangeListener(event -> teamDataProvider.addFilter(
				team -> StringUtils.containsIgnoreCase(Integer.toString(team.getId()), teamIDField.getValue())));
		teamIDField.setValueChangeMode(ValueChangeMode.EAGER);
		teamFilterRow.getCell(id).setComponent(teamIDField);
		teamIDField.setSizeFull();
		teamIDField.setPlaceholder("ID");

		// Sekond column filter
		TextField nameField = new TextField();
		nameField.addValueChangeListener(event -> teamDataProvider
				.addFilter(team -> StringUtils.containsIgnoreCase(team.getName(), nameField.getValue())));
		nameField.setValueChangeMode(ValueChangeMode.EAGER);
		teamFilterRow.getCell(name).setComponent(nameField);
		nameField.setSizeFull();
		nameField.setPlaceholder("name");

		// Sekond column filter
		TextField managedByField = new TextField();
		managedByField.addValueChangeListener(event -> teamDataProvider
				.addFilter(team -> StringUtils.containsIgnoreCase(team.getManagedBy(), managedByField.getValue())));
		managedByField.setValueChangeMode(ValueChangeMode.EAGER);
		teamFilterRow.getCell(managedBy).setComponent(managedByField);
		managedByField.setSizeFull();
		managedByField.setPlaceholder("name");

		add(teamGrid);
	}
}
