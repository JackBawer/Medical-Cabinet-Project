package model;

import java.time.LocalDateTime;

public class MedicalRecord extends Record{
	private String observation;
	public Prescription prescription;
	
	public MedicalRecord(LocalDateTime dateTime, String observation, Prescription prescription) {
		super(dateTime);
		this.observation = observation;
		this.prescription = prescription;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	
	@Override
	public String getRecordDetails() {
		return "Date: " + getDateTime() + ", Observations: " + observation + ", Prescription: " + prescription;
	}
}