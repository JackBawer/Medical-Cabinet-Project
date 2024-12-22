package module;

import java.time.LocalDateTime;

public class MedicalCertificate extends Record{
	private Doctor issuedBy;
	private Summary summary;
	private int rest;
	
	public MedicalCertificate(LocalDateTime dateTime, Patient patient, Doctor issuedBy, Summary summary, int rest) {
		super(dateTime, patient);
		this.issuedBy = issuedBy;
		this.summary = summary;
		this.rest = rest;
	}
	
	public Doctor getIssuedBy() {
		return issuedBy;
	}
	
	public void setIssuedBy(Doctor issuedBy) {
		this.issuedBy = issuedBy;
	}
	
	public Summary getSummary() {
		return summary;
	}
	
	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	
	public int getRest() {
		return rest;
	}
	
	public void setRest(int rest) {
		this.rest = rest;
	}
	
	@Override
	public String getRecordDetails() {
		return getPatient().getFirstName() + " " + getPatient().getLastName() + 
				" is excused the absence of " + getRest() + " days due to " +
				getSummary() + ", issued by " + getIssuedBy().getFirstName() + " " + getIssuedBy().getLastName() +
				"on " + getDateTime();
	}
}
