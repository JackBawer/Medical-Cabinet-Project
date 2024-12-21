package service;

import model.MedicalRecord;
import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService {
	private List<MedicalRecord> records = new ArrayList<>();
	
	public void addMedicalRecord(MedicalRecord record) {
		records.add(record);
	}
	
	public List<MedicalRecord> getPatientRecords(Patient patient) {
		List<MedicalRecord> patientRecords = new ArrayList<>();
		for (MedicalRecord record: records) {
			// Observation observation = record.getObservation();
			if (record.getObservation().getDescription().contains(patient.getLastName())) {
				patientRecords.add(record);
			}
		}
		
		return patientRecords;
	}
}
