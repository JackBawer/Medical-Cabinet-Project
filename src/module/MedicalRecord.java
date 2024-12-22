package module;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class MedicalRecord extends Record{
	private List<Consultation> consultations;
	private List<Observation> observations;
	private List<Prescription> prescriptions;
	private List<MedicalCertificate> medicalCertificates;
	private List<MedicalHistory> medicalHistory;
	
	public MedicalRecord(LocalDateTime dateTime, Patient patient) {
		super(dateTime, patient);
		this.consultations = new ArrayList<>();
		this.observations =  new ArrayList<>();
		this.prescriptions = new ArrayList<>();
		this.medicalCertificates = new ArrayList<>();
		this.medicalHistory = new ArrayList<>();
	}
	
	
	public List<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	public List<Observation> getObservations() {
		return observations;
	}

	public void setObservations(List<Observation> observations) {
		this.observations = observations;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public List<MedicalCertificate> getMedicalCertificates() {
		return medicalCertificates;
	}

	public void setMedicalCertificates(List<MedicalCertificate> medicalCertificates) {
		this.medicalCertificates = medicalCertificates;
	}

	public List<MedicalHistory> getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(List<MedicalHistory> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	@Override
	public String getRecordDetails() {
		return "MedicalRecord{" +
		       "dateTime: " + getDateTime() +
		       ", patient: " + getPatient().getLastName() + ", " + getPatient().getFirstName() +
		       ", consultations: " + consultations.size() + " consultation(s)" +
		       ", observations: " + observations.size() + " observation(s)" +
		       ", prescriptions: " + prescriptions.size() + " prescription(s)" +
		       ", medicalCertificates: " + medicalCertificates.size() + " certificate(s)" +
		       ", medicalHistory: " + medicalHistory.size() + " history record(s)" +
		       '}';
	}

}