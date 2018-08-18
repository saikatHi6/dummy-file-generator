package org.atom.genarator.dto;

import org.springframework.stereotype.Component;

/**
 * 
 * @author SSadhukhan
 *
 * This is to generate only fixed no of field  
 *
 *
 */


@Component
public class Employee {
	
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNo;
	
	
	
	/*public Employee(int id, String firstName, String lastName, String phoneNo) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
	}*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNo=" + phoneNo
				+ "]";
	}
	
	

}
