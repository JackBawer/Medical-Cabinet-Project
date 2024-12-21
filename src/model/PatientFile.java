package model;


import java.util.ArrayList;
import java.util.List;

public class PatientFile {
	private Patient patient;
	private List<Observation> medicalHistory;
	private List<String> surgicalHistory;
	private List<MedicalRecord> treatmentSummary;
	public PatientFile(Patient patient) {
		this.patient = patient;
		this.medicalHistory = medicalHistory;
		this.surgicalHistory = surgicalHistory;
		this.treatmentSummary = treatmentSummary;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public List<Observation> getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(List<Observation> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	public List<String> getSurgicalHistory() {
		return surgicalHistory;
	}
	public void setSurgicalHistory(List<String> surgicalHistory) {
		this.surgicalHistory = surgicalHistory;
	}
	public List<MedicalRecord> getTreatmentSummary() {
		return treatmentSummary;
	}
	public void setTreatmentSummary(List<MedicalRecord> treatmentSummary) {
		this.treatmentSummary = treatmentSummary;
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Patient: ").append(patient).append("\n")
          .append("Medical History: ").append(medicalHistory).append("\n")
          .append("Surgical History: ").append(surgicalHistory).append("\n")
          .append("Treatment Summary:\n");
        for (MedicalRecord record : treatmentSummary) {
            sb.append(record.getRecordDetails()).append("\n");
        }
        return sb.toString();
    }
}
