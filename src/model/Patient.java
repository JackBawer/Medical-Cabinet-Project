package model;

public class Patient extends User {
	private String phoneNumber;
	
	public Patient(String firstName, String lastName, String phoneNumber) {
		super(firstName, lastName);
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
		
	@Override 
	public String getDetails() {
		return getFirstName() + " " + getLastName() + " (" + phoneNumber + ")";
	}
}
