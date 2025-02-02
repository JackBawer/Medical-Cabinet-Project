package service;

import module.PatientFile;
import java.util.ArrayList;
import java.util.List;

public class PatientFileService {
    private List<PatientFile> patientFiles;

    // Constructor that initializes the patientFiles list based on the passed argument
    public PatientFileService(List<PatientFile> patientFiles) {
        if (patientFiles == null) {
            this.patientFiles = new ArrayList<>();
        } else {
            this.patientFiles = patientFiles;
        }
    }

    // Get all patient files
    public List<PatientFile> getPatientFiles() {
        return this.patientFiles;
    }

    // Set a new list of patient files
    public void setPatientFiles(List<PatientFile> patientFiles) {
        this.patientFiles = patientFiles;
    }

    // Add a new patient file to the list
    public void addPatientFile(PatientFile patientFile) {
        for (PatientFile file : patientFiles) {
            if (file.getPatient().getId().equals(patientFile.getPatient().getId())) {
                System.out.println("Patient with ID " + patientFile.getPatient().getId() + " already exists.");
                return;
            }
        }
        this.patientFiles.add(patientFile);
    }

    // Get a patient file by their ID
    public PatientFile getPatientFile(String ID) {
        if (ID == null || ID.isEmpty()) {
            System.out.println("Invalid ID provided.");
            return null;
        }

        for (PatientFile file : this.patientFiles) {
            if (file.getPatient().getId().equals(ID)) {
                return file;
            }
        }
        System.out.println("No patient file found for patient with ID: " + ID);
        return null;
    }

    // Update an existing patient file
    public void updatePatientFile(PatientFile patientFile) {
        if (patientFile == null || patientFile.getPatient() == null) {
            System.out.println("Invalid patient file.");
            return;
        }

        for (int i = 0; i < this.patientFiles.size(); i++) {
            if (this.patientFiles.get(i).getPatient().getId().equals(patientFile.getPatient().getId())) {
                this.patientFiles.set(i, patientFile); // Update the patient file in the list
                return;
            }
        }
        System.out.println("No patient file found for patient with ID: " + patientFile.getPatient().getId());
    }
}
