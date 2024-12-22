package module;

public class Medication {
	private String medicationName;
	private int dosage;
	private int duration;
	
	public Medication(String medicationName, int dosage, int duration) {
		this.medicationName = medicationName;
		this.dosage = dosage;
		this.duration = duration;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setName(String name) {
		this.medicationName = medicationName;
	}

	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
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
		return "Medication: " + getMedicationName() + ", Dosage: " + getDosage() + ", Duration: " + getDuration();
	}
}
