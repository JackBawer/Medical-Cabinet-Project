package model;

public class Prescription {
	private String medicationName;
	private String dosage;
	private int duration; // in days
	
	public Prescription(String medicationName, String dosage, int duration) {
		this.medicationName = medicationName;
		this.dosage = dosage;
		this.duration = duration;
	}
	
	public String getMedicationName() {
		return medicationName;
	}
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return medicationName + " (" + dosage + ") " + duration + " days";
	}
}
