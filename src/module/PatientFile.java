package module;


import java.util.ArrayList;
import java.util.List;

public class PatientFile {
	private Patient patient;
	private List<MedicalHistory> medicalHistory;
	
	public PatientFile(Patient patient, List<MedicalHistory> medicalHistory, List<Summary> summary) {
		this.patient = patient;
		this.medicalHistory = new ArrayList<>();
	}

	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public List<MedicalHistory> getMedicalHistory() {
		return medicalHistory;
	}
	
	public void setMedicalHistory(List<MedicalHistory> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	
	
    @Override
	public String toString() {
    	return getPatient().getDetails() + ", Medical history: " + getMedicalHistory();
    }
}
