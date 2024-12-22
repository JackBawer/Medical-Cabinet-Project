package module;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Prescription extends Record{
	private List<Medication> medications;
	
	public Prescription(LocalDateTime dateTime, Patient patient, List<Medication> medications) {
		super(dateTime, patient);
		this.medications = new ArrayList<>();
	}
	
	public List<Medication> getMedications() {
		return this.medications;
	}
	
	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

	@Override
	public String getRecordDetails() {
		return "Prescription: " + getMedications();
	}
}
