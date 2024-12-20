package model;

import java.time.LocalDateTime;

public class Appointment extends Record{
	private Patient patient;

	public Appointment(LocalDateTime dateTime, Patient patient) {
		super(dateTime);
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@Override
	public String getRecordDetails() {
		return "Appointment with " + patient.getDetails() + " on " + getDateTime();
	}
}
