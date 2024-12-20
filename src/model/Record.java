package model;

import java.time.LocalDateTime;

public abstract class Record {
	private LocalDateTime dateTime;

	public Record(LocalDateTime dateTime) {
		super();
		this.dateTime = dateTime;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public abstract String getRecordDetails();
}
