package model;

public class Patient extends Person {
	private String phoneNumber;
	private String address;
	
	public Patient(String firstName, String lastName, String phoneNumber, String address) {
		super(firstName, lastName);
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@Override 
	public String getDetails() {
		return getFirstName() + " " + getLastName() + " (" + phoneNumber + ")";
	}
}
