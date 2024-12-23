package module;

public class Patient extends User {
	private String id;
	private String phoneNumber;
	
	public Patient(String id, String firstName, String lastName, String phoneNumber) {
		super(firstName, lastName);
		this.id = id;
		this.phoneNumber = phoneNumber;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override 
	public String getDetails() {
		return getId() + " " + getFirstName() + " " + getLastName() + " (" + getPhoneNumber() + ")";
	}
}
