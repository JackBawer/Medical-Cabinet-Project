package module;

public class Doctor extends User {
	private String doctorId;
	private String specialization;

	public Doctor(String doctorId, String firstName, String lastName, String specialization) {
		super(firstName, lastName);
		this.doctorId = doctorId;
		this.specialization = specialization;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@Override
	public String getDetails() {
		return "Dr " + getFirstName() + " " + getLastName() + " (" + specialization + ")";
	}
}
