package com.employee.vaadin.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;



@Entity
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Creating properties for Entity Employee. These will be presented as columns
	// on tables
	@Id
	@Size(min = 1, max = 11)
	private String id;

	@Size(min = 2, max = 30)
	private String firstName;

	@Size(min = 2, max = 30)
	private String lastName;

	private ActiveStatus activeStatus;

	@Email
	private String emailAddress;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "teamid")
//	private Team team;
	
	private int teamId;
	
	
	//Generating Setter and Getter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ActiveStatus getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(ActiveStatus activestatus) {
		this.activeStatus = activestatus;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}


//	public Team getTeam() {
//		return team;
//	}
//
//	public void setTeam(Team team) {
//		this.team = team;
//	}

//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", activestatus="
//				+ activestatus + ", emailAddress=" + emailAddress + ", team=" + team + "]";
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
