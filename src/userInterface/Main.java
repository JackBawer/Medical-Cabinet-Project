package userInterface;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import module.*;
import service.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		List<PatientFile> patientFiles = new ArrayList<>();
		List<MedicalRecord> medicalRecords = new ArrayList<>();

		MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecords);
		PatientFileService patientFileService = new PatientFileService(patientFiles);
		AppointmentService appointmentService = new AppointmentService();
		DoctorService doctorService = new DoctorService();

		UI ui = new UI(scanner, medicalRecordService, patientFileService, appointmentService, doctorService);
		ui.start();
	}
}
