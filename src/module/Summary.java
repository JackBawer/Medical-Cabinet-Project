package module;

public class Summary {
	private String summary;

	public Summary(String summary) {
		super();
		this.summary = summary;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Override
	public String toString() {
		return summary;
	}
}
