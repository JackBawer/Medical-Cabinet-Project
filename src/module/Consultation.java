package module;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Consultation extends Record{
	private Doctor doctor;
	private Observation observation;
	private Prescription prescription;
	private MedicalCertificate medicalCertificate;
	private Summary summary;
	
	public Consultation(LocalDateTime dateTime, Patient patient, Doctor doctor, Observation observation, Prescription prescription,
			MedicalCertificate medicalCertificate, Summary summary) {
		super(dateTime, patient);
		this.doctor = doctor;
		this.observation = observation;
		this.prescription = prescription;
		this.medicalCertificate = medicalCertificate;
		this.summary = summary;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public Observation getObservation() {
		return observation;
	}
	
	public void setObservation(Observation observation) {
		this.observation = observation;
	}
	
	public Prescription getPrescription() {
		return prescription;
	}
	
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	
	public MedicalCertificate getMedicalCertificate() {
		return medicalCertificate;
	}
	
	public void setMedicalCertificate(MedicalCertificate medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}
	
	public Summary getSummary() {
		return summary;
	}
	
	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	
	@Override
	public String getRecordDetails() {
		return "Consultation{" +
		        "dateTime: " + getDateTime() +
		        ", patient: " + getPatient().getLastName() + ", " + getPatient().getFirstName() +
		        ", doctor: " + doctor.getLastName() + ", " + doctor.getFirstName() +
		        ", observation: " + observation.getDescription() +
		        ", prescription: " + (prescription != null ? prescription.toString() : "None") +
		        ", medicalCertificate: " + (medicalCertificate != null ? medicalCertificate.toString() : "None") +
		        "Summary: " + summary +
		        '}';
	}
}
