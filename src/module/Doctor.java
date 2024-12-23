package module;

public class Doctor extends User {
	private String specialization;
	// private boolean occupied;
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
