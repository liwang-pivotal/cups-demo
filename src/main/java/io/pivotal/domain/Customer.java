package io.pivotal.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

@Region("customer")
public class Customer {
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	
	public Customer() {}

	public Customer(String id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

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

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

}
