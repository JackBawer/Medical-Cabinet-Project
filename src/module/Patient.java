package module;

public class Patient extends User {
	private String id;
	private String phoneNumber;
	private MedicalRecord medicalRecord;
	
	public Patient(String id, String firstName, String lastName, String phoneNumber, MedicalRecord medicalRecord) {
		super(firstName, lastName);
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.medicalRecord = medicalRecord;
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
		
	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	@Override 
	public String getDetails() {
		return getId() + " " + getFirstName() + " " + getLastName() + " (" + phoneNumber + ")";
	}
}
