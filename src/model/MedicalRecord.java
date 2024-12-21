package model;

import java.time.LocalDateTime;

public class MedicalRecord extends Record{
	private Observation observation;
	public Prescription prescription;
	public String history;
	
	public MedicalRecord(LocalDateTime dateTime, Observation observation, Prescription prescription, String history) {
		super(dateTime);
		this.observation = observation;
		this.prescription = prescription;
		this.history = history;
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
	
	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	@Override
	public String getRecordDetails() {
		return "Date: " + getDateTime() + ", Observations: " + observation + ", Prescription: " + prescription + ", History: " + history;
	}
}