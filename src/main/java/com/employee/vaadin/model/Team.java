package com.employee.vaadin.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



@Entity
public class Team implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Create properties for table Team 
	@Id
	private int id;
	@Size(min = 1, max = 10)
	private String name;

	@Size(min = 1, max = 10)
	private String managedBy;
	
//	@NotNull 
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")
//	private Set<Employee> comments = new HashSet<>();
	
	/// generate setter and getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManagedBy() {
		return managedBy;
	}
	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}
	
	// Overriding esential method
	@Override
	public String toString() {
		return "Team [ID=" + id + ", name=" + name + ", managedBy=" + managedBy + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
