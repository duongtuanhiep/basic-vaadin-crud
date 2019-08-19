package com.employee.vaadin.frontend;

import org.springframework.beans.factory.annotation.Autowired;

import com.employee.vaadin.converter.StringToActiveStatusConverter;
import com.employee.vaadin.dao.EmployeeRepo;
import com.employee.vaadin.model.ChangeHandler;
import com.employee.vaadin.model.Employee;

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
public class EmployeeCRUDUI extends VerticalLayout implements KeyNotifier {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeRepo employeerepo;

	// Generating input field
	TextField id = new TextField("id");
	TextField firstName = new TextField("First name");
	TextField lastName = new TextField("Last name");
	TextField emailAddress = new TextField("Email");
	TextField activeStatus = new TextField("Status");
	TextField teamId = new TextField("Team ID");

	/* Dividing text field and buttons into smaller components */
	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	Button addnew = new Button("Add New", VaadinIcon.NURSE.create());
	Button delete = new Button("Delete", VaadinIcon.EXCLAMATION_CIRCLE_O.create());
	Button edit = new Button("Edit", VaadinIcon.EDIT.create());

	// Wrapper components
	HorizontalLayout topWrapper = new HorizontalLayout();
	VerticalLayout bottomWrapper = new VerticalLayout();
	HorizontalLayout spanInput1 = new HorizontalLayout(id, firstName, lastName);
	HorizontalLayout spanInput2 = new HorizontalLayout(emailAddress, activeStatus, teamId);
	HorizontalLayout spanButton = new HorizontalLayout(save, cancel);

	// Creating binder
	Binder<Employee> binder = new Binder<>(Employee.class);

	//create an interface to handle change from backend (Create - update - delete) operation
	private ChangeHandler changeHandler;

	// Constructor
	@Autowired
	public EmployeeCRUDUI(EmployeeRepo employeerepo) {
		
		// Wiring Spring dependency Injection for repository class to JPA Repository
		// implementation
		this.employeerepo = employeerepo;

		// Adding components into wrapper
		bottomWrapper.add(spanInput1, spanInput2, spanButton);
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
		binder.forField(teamId).withConverter(new StringToIntegerConverter("Wrong Format")).bind(Employee::getTeamId,
				Employee::setTeamId);
		binder.forField(activeStatus).withConverter(new StringToActiveStatusConverter()).bind(Employee::getActiveStatus,
				Employee::setActiveStatus);
		binder.bindInstanceFields(this);

		// Binding function for buttons
		save.addClickListener(e ->	saveEmployee());
		cancel.addClickListener(e -> bottomWrapper.setVisible(false));
		edit.addClickListener(e -> 	bottomWrapper.setVisible(true));
		addnew.addClickListener(e -> bottomWrapper.setVisible(true));
		delete.addClickListener(e -> deleteEmployee());
		
		// make the component invisible and works as a popsup box only
		bottomWrapper.setVisible(false);
		
		// Disable function of button when no Employee row on the grid is selected 
		delete.setEnabled(false);
		edit.setEnabled(false);
	}
	
	// creating new instance of pojo and rewrite it.
	void saveEmployee() {
		Employee employee = new Employee();
		try {
			binder.writeBean(employee);
			employeerepo.save(employee);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		bottomWrapper.setVisible(false);
		changeHandler.onChange();
	}
	
	//delete data off database using bean
	void deleteEmployee() {
		employeerepo.delete(binder.getBean());
		changeHandler.onChange();
	}
	
	//fetching data from grid to the textfields and display the textfield
	public final void editEmployee(Employee employeeIn) {
		Employee employeeOut;
		if (employeeIn == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = employeeIn.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			employeeOut = employeerepo.findById(employeeIn.getId()).get();
		} else {
			employeeOut = employeeIn;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(employeeOut);

		// Focus first name initially
		firstName.focus();
	}
	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		changeHandler = h;
	}
}
