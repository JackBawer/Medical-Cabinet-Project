package module;

import java.time.LocalDateTime;

import java.util.List;

public class MedicalRecord extends Record{
	private List<Consultation> consultations;
	
	public MedicalRecord(LocalDateTime dateTime, Patient patient, List<Consultation> consultations) {
		super(dateTime, patient);
		this.consultations = consultations;
	}
	
	public List<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	@Override
	public String getRecordDetails() {
		return "MedicalRecord{" +
		       "dateTime: " + getDateTime() +
		       ", patient: " + getPatient().getLastName() + ", " + getPatient().getFirstName() +
		       ", consultations: " + consultations.size() + " consultation(s)" +
		       '}';
	}

}