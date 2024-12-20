package model;

public class Secretary extends Person{

	public Secretary(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	@Override
	public String getDetails() {
		return "Secretary: " + getFirstName() + " " + getLastName();
	}
	
	
}
