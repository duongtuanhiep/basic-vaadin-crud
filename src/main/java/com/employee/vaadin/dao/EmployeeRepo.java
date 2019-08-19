package com.employee.vaadin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.vaadin.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String> {

}
