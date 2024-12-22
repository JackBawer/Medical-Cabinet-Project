package service;

import module.PatientFile;
import module.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientFileService  {
	
	private List<PatientFile> patientFiles;
	
	public PatientFileService(List<PatientFile> patientFiles) {
		this.patientFiles = new ArrayList<>();
	}
	
	public List<PatientFile> getPatientFiles() {
		return patientFiles;
	}

	public void setPatientFiles(List<PatientFile> patientFiles) {
		this.patientFiles = patientFiles;
	}


	void addPatient(Patient patient) {
		return;
	}
	
	void updatePatientInfo(Patient patient) {
		return;
	}
	
	void getPatientInfo(String lastName, String firstName) {
		return;
	}
	void getPatientHistory(Patient patient) {
		return;
	}
	
	public String toString() {
		return "This will be so long.";
	}
 }