package module;

import java.time.LocalDateTime;

public abstract class Record {
	private LocalDateTime dateTime;
	private Patient patient;
	
	public Record(LocalDateTime dateTime, Patient patient) {
		this.dateTime = dateTime;
		this.patient = patient;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public abstract String getRecordDetails();
}
