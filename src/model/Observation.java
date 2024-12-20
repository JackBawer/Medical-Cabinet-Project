package model;

public class Observation {
	private String description;

	public Observation(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Observation: " + description;
	}
}
