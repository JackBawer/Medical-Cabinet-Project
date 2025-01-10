package module;

import java.util.List;

public class PatientFile {
	private Patient patient;
	private List<MedicalHistory> medicalHistory;
	private List<Summary> summaries;

	// Constructor
	public PatientFile(Patient patient, List<MedicalHistory> medicalHistory, List<Summary> summaries) {
		this.patient = patient;
		this.medicalHistory = medicalHistory;
		this.summaries = summaries;
	}

	// Getter for patient
	public Patient getPatient() {
		return patient;
	}

	// Other getters and setters
	public List<MedicalHistory> getMedicalHistory() {
		return medicalHistory;
	}

	public List<Summary> getSummaries() {
		return summaries;
	}
}
