package module;

public class Observation {
	private String description;
	
	public Observation(String description) {
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
