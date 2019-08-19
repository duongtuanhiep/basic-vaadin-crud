package com.employee.vaadin.frontend;

import org.springframework.beans.factory.annotation.Autowired;

import com.employee.vaadin.dao.TeamRepo;
import com.employee.vaadin.model.ChangeHandler;
import com.employee.vaadin.model.Team;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;



@SpringComponent
@UIScope
public class TeamCRUDUI extends VerticalLayout implements KeyNotifier {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TeamRepo teamRepo;

	// Generating input field
	TextField id = new TextField("ID");
	TextField managedBy = new TextField("managedBy");
	TextField name = new TextField("name");

	/* Dividing text field and buttons into smaller components */
	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	Button addnew = new Button("Add New", VaadinIcon.NURSE.create());
	Button delete = new Button("Delete", VaadinIcon.EXCLAMATION_CIRCLE_O.create());
	Button edit = new Button("Edit", VaadinIcon.EDIT.create());

	// Wrapper components
	HorizontalLayout topWrapper = new HorizontalLayout();
	VerticalLayout bottomWrapper = new VerticalLayout();
	HorizontalLayout spanInput1 = new HorizontalLayout(id, managedBy, name);
	HorizontalLayout spanButton = new HorizontalLayout(save, cancel);

	// Creating binder
	Binder<Team> binder = new Binder<>(Team.class);

	//create an interface to handle change from backend (Create - update - delete) operation
	private ChangeHandler changeHandler;

	// Constructor
	@Autowired
	public TeamCRUDUI(TeamRepo teamRepo) {
		// Wiring Spring dependency Injection for repository class to JPA Repository
		// implementation
		this.teamRepo = teamRepo;

		// Adding components into wrapper
		bottomWrapper.add(spanInput1, spanButton);
		topWrapper.add(addnew, edit, delete);
		add(topWrapper, bottomWrapper);
		// CSS - Bottom Component:
		setSpacing(true);
		save.getElement().getThemeList().add("primary");
		// CSS - Top Component:
		addnew.getElement().getThemeList().add("primary");
		edit.getElement().getThemeList().add("tiertary");
		delete.getStyle().set("background-color", "red");
		delete.getStyle().set("color", "white");
		delete.getElement().getThemeList().add("tiertary");

		// Binding field to properties in POJO class
		binder.forField(id).withConverter(new StringToIntegerConverter("Wrong Format")).bind(Team::getId,
				Team::setId);
		binder.bindInstanceFields(this);

		// Binding function for buttons
		save.addClickListener(e ->	saveTeam());
		cancel.addClickListener(e -> bottomWrapper.setVisible(false));
		edit.addClickListener(e -> 	bottomWrapper.setVisible(true));
		addnew.addClickListener(e -> bottomWrapper.setVisible(true));
		delete.addClickListener(e -> deleteTeam());
		// make the component invisible and works as a popsup box only
		bottomWrapper.setVisible(false);
		// Disable function of button when no Team row on the grid is selected 
		delete.setEnabled(false);
		edit.setEnabled(false);
	}

	void saveTeam() {
		Team team = new Team();
		try {
			binder.writeBean(team);
			teamRepo.save(team);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		bottomWrapper.setVisible(false);
		changeHandler.onChange();
	}

	void deleteTeam() {
		teamRepo.delete(binder.getBean());
		changeHandler.onChange();
	}

	public final void editTeam(Team teamIn) {
		Team teamOut;
		if (teamIn == null) {
			setVisible(false);
			return;
		}
		Integer number = teamIn.getId();
		final boolean persisted = number != null;
		if (persisted) {
			// Find fresh entity for editing
			teamOut = teamRepo.findById(teamIn.getId()).get();
		} else {
			teamOut = teamIn;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(teamOut);

		// Focus first name initially
		id.focus();
	}
	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		changeHandler = h;
	}
}
