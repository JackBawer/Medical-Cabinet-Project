package model;

public class Doctor extends Person {
	private String specialization;

	public Doctor(String firstName, String lastName, String specialization) {
		super(firstName, lastName);
		this.specialization = specialization;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	@Override
	public String getDetails() {
		return "Dr " + getFirstName() + " " + getLastName() + "(" + specialization + ")";
	}
}
