package module;

import java.time.LocalDateTime;
import java.util.List;

public class Prescription extends Record {
	private List<Medication> medications;

	public Prescription(LocalDateTime dateTime, Patient patient, List<Medication> medications) {
		super(dateTime, patient);
		this.medications = medications;
	}

	public List<Medication> getMedications() {
		return this.medications;
	}

	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

	@Override
	public String getRecordDetails() {
		StringBuilder details = new StringBuilder("Prescription Date: " + getDateTime() + "\nMedications:\n");
		for (Medication medication : medications) {
			details.append(medication.toString()).append("\n");
		}
		return details.toString();
	}

	@Override
	public String toString() {
		return getRecordDetails();
	}
}