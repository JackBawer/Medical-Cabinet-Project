package service;

import module.PatientFile;

import java.util.ArrayList;
import java.util.List;

public class PatientFileService {

    private List<PatientFile> patientFiles;

    public PatientFileService(List<PatientFile> patientFiles) {
        this.patientFiles = new ArrayList<>();
  }

    public List<PatientFile> getPatientFiles() {

        return this.patientFiles;
    }

    public void setPatientFiles(List<PatientFile> patientFiles) {
        this.patientFiles = patientFiles;
    }

    void addPatientFile(PatientFile patientFile) {
        this.patientFiles.add(patientFile);
    }

    public PatientFile getPatientFile(String ID) {
        for (PatientFile file: this.patientFiles) {
            if (file.getPatient().getId().equals(ID)) {
                return file;
            }
        }
        System.out.println("No patient file found for patient with ID: " + ID);
        return null;
    }

    public void updatePatientFile(PatientFile patientFile) {
        for (PatientFile file: this.patientFiles) {
            if (file.getPatient().getId().equals(patientFile.getPatient().getId())) {
                file = patientFile;
                return;
            }
        }

        System.out.println("No patient file found for patient with ID: " + patientFile.getPatient().getId());
    }
}