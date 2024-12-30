package userInterface;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

import module.*;
import service.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		List<PatientFile> patientFiles = new ArrayList<>();
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		List<Consultation> consultations = new ArrayList<>();
		List<Medication> medications = new ArrayList<>();

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
		
		int option = scanner.nextInt();
		switch (option) {
			case 1:
				System.out.println("Welcome, Doctor!");
				Doctor doctor = new Doctor("John", "Doe", "General Practitioner");
				while (true) {
					System.out.println("""
							1. Begin consultation
							2. Manage medical records
							3. Exit
							""");
					int doctorOption = scanner.nextInt();
					switch (doctorOption) {
						case 1:
						//gonna patient later im tired
							System.out.println("Describe how the patient feels:");
							String description = scanner.next();
							Observation observation = new Observation(description);
							Prescription prescription = new Prescription(LocalDateTime.now(), null, medications);
							System.out.println("Prescribe medication: ");
							boolean prescribing = true;
							while (prescribing) {
								System.out.println("Enter the name of the medication: ");
								String medicationName = scanner.next();
								System.out.println("Enter the dosage: ");
								String dosage = scanner.next();
								System.out.println("Enter the duration: ");
								String duration = scanner.next();
								Medication medication = new Medication(medicationName, Integer.parseInt(dosage), Integer.parseInt(duration));
								medications.add(medication);
								System.out.println("Prescribe another medication? (Y/N)");
								String answer = scanner.next();
								if (answer.equals("N")) {
									prescribing = false;
								}
							}
							System.out.println("Issue a quick summary: ");
							String quickSummary = scanner.next();

							System.out.println("Issue a medical certificate? (Y/N)");
							String issueCertificate = scanner.next();
							if (issueCertificate.equals("Y")) {
								System.out.println("Summary: ");
								String summary = scanner.next();
								System.out.println("Rest: ");
								int rest = scanner.nextInt();
								MedicalCertificate medicalCertificate = new MedicalCertificate(LocalDateTime.now(), null, doctor, new Summary(summary), rest);	
								Consultation consultation = new Consultation(LocalDateTime.now(), null, doctor, observation, prescription, medicalCertificate, new Summary(quickSummary));
								consultations.add(consultation);
							} else {
								Consultation consultation = new Consultation(LocalDateTime.now(), null, doctor, observation, prescription, null, new Summary(quickSummary));
								consultations.add(consultation);
							}
							break;
						case 2:
							System.out.println("Manage medical records");
							while (true) {
								System.out.println("""
										1. Add medical record
										2. Update medical record
										3. Delete medical record
										4. Exit
										""");
								int manageMedicalRecordOption = scanner.nextInt();
								switch (manageMedicalRecordOption) {
									case 1:
										System.out.println("Add medical record");
										System.out.println("Enter the patient's details: ");
										System.out.println("ID: ");
										String patientId = scanner.next();
										System.out.println("First name: ");	
										String firstName = scanner.next();
										System.out.println("Last name: ");
										String lastName = scanner.next();
										System.out.println("Phone number: ");
										String phoneNumber = scanner.next();
										Patient patient = new Patient(patientId, firstName, lastName, phoneNumber);
										MedicalRecord medicalRecord = new MedicalRecord(LocalDateTime.now(), patient, consultations);
										medicalRecordService.addMedicalRecord(medicalRecord);
										break;
									case 2:
										System.out.println("Please enter the patient ID: ");
										String patientID = scanner.next();
										MedicalRecord record = medicalRecordService.getMedicalRecord(patientID);
										if (record != null) {
											Consultation consultation = new Consultation(LocalDateTime.now(), record.getPatient(), null, null, null, null, null);
											record.getConsultations().add(consultation);
											medicalRecordService.updateRecord(record);
										} else {
											System.out.println("No medical record found for patient with ID: " + patientID);
										}
										break;
									case 3:
										System.out.println("Please enter the patient ID: ");
										String ID = scanner.next();
										medicalRecordService.deleteRecord(ID);
										break;
									case 4:
										break;
									default:
										System.out.println("Invalid option");
								}
							} 
							
						case 3:
							System.out.println("Exit");
							break;
						default:
							System.out.println("Invalid option");
							break;
					}		
				}
				

				
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
		scanner.close();
	}
	

}
