package service;

import module.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService {
    private List<MedicalRecord> medicalRecords;

    public MedicalRecordService(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = new ArrayList<>();
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
    }

    public MedicalRecord getMedicalRecord(String ID) {
        for (MedicalRecord record : medicalRecords) {
            if (record.getPatient().getId().equals(ID)) {
                return record;
            }
        }
        System.out.println("No medical record found for patient with ID: " + ID);
        return null;
    }

    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecords.get(i).getPatient().getId().equals(medicalRecord.getPatient().getId())) {
                medicalRecords.set(i, medicalRecord);
                return;
            }
        }
        System.out.println("No medical record found for patient with ID: " + medicalRecord.getPatient().getId());
    }
    public void deleteRecord(String ID) {
        for (MedicalRecord record : medicalRecords) {
            if (record.getPatient().getId() == ID) {
                medicalRecords.remove(record);
                System.out.println("Medical record deleted successfully");
                return;
            }
        }
        System.out.println("No medical record found for patient with ID: " + ID);
    }
}
