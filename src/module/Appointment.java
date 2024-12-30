package module;

import java.time.LocalDateTime;

public class Appointment extends Record{
	private Secretary secretary;
	private Doctor doctor;
	private String status;
	
	public Appointment(LocalDateTime dateTime, Patient patient, Secretary secretary, String status) {
		super(dateTime, patient);
		this.secretary = secretary;
		this.status = status;
	}
	
	public Secretary getSecretary() {
		return secretary;
	}
	
	public void setSecretary(Secretary secretary) {
		this.secretary = secretary;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getRecordDetails() {
		return getStatus() + "Appointment with " + getPatient().getDetails() + " with " + doctor.getDetails() + " on " + getDateTime();
	}
}
