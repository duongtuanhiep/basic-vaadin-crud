package com.employee.vaadin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.vaadin.model.Team;

public interface TeamRepo extends JpaRepository<Team, Integer>{
}
