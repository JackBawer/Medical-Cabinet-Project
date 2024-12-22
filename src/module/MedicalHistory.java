package module;

public class MedicalHistory {
	private String medicalHistory;

	public MedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	
	@Override
	public String toString() {
		return "Medical history: " + this.medicalHistory;
	}
}
