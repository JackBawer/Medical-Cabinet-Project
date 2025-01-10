package module;

import java.time.LocalDateTime;
import java.util.List;

public class MedicalRecord extends Record {
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

	public void addConsultation(Consultation consultation) {
		this.consultations.add(consultation);
	}

	@Override
	public String getRecordDetails() {
		return "Medical Record Details:\n" +
				"Date/Time: " + getDateTime() + "\n" +
				"Patient: " + getPatient().getFirstName() + " " + getPatient().getLastName() + "\n" +
				"Consultations: " + consultations.size() + " consultation(s)";
	}
}
