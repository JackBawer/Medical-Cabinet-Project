package userInterface;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import module.*;
import service.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		List<PatientFile> patientFiles = new ArrayList<>();
		List<MedicalRecord> medicalRecords = new ArrayList<>();

		MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecords);
		PatientFileService patientFileService = new PatientFileService(patientFiles);
		AppointmentService appointmentService = new AppointmentService();

		System.out.println("Welcome User!");
		System.out.println("Please select an option:");

		System.out.println("""
				1. Doctor
				2. Secretary
				3. ¨Patient
				4. Exit
				""");
		// gonna think about how to implement the switch statement
		int option = scanner.nextInt();
		switch (option) {
			case 1:
				System.out.println("Welcome, Doctor!");
				break;
			case 2:
				System.out.println("Secretary");
				break;
			case 3:
				System.out.println("Patient");
				break;
			case 4:
				System.out.println("Exit");
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}

}
