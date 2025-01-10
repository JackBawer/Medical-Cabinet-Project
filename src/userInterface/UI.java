package userInterface;

import java.util.Scanner;
import service.*;
import module.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class UI {
    private Scanner scanner;
    private MedicalRecordService medicalRecordService;
    private PatientFileService patientFileService;
    private AppointmentService appointmentService;

    public UI(Scanner scanner, MedicalRecordService medicalRecordService,
              PatientFileService patientFileService, AppointmentService appointmentService) {
        this.scanner = scanner;
        this.medicalRecordService = medicalRecordService;
        this.patientFileService = patientFileService;
        this.appointmentService = appointmentService;
    }

    public void start() {
        System.out.println("Welcome User!");
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("""
                    1. Doctor
                    2. Secretary
                    3. Patient
                    4. Exit
                    """);

            int option = Integer.parseInt(scanner.nextLine());
            if (option == 4) {
                System.out.println("Exiting the application...");
                break;
            }

            handleOption(option);
        }
    }

    private void handleOption(int option) {
        switch (option) {
            case 1 -> handleDoctor();
            case 2 -> handleSecretary();
            case 3 -> handlePatient();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void handleDoctor() {
        System.out.println("Welcome, Doctor!");
        while (true) {
            System.out.println("Options:");
            System.out.println("""
            1. View your appointments
            2. Conduct consultation
            3. Exit
            """);
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter your doctor ID:");
                    String doctorId = scanner.nextLine();
                    List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
                    if (appointments.isEmpty()) {
                        System.out.println("No appointments found for doctor ID: " + doctorId);
                    } else {
                        appointments.forEach(appointment -> System.out.println(appointment.getRecordDetails()));
                    }
                }
                case 2 -> {
                    System.out.println("Enter your doctor ID:");
                    String doctorId = scanner.nextLine();
                    System.out.println("Enter patient ID:");
                    String patientId = scanner.nextLine();

                    // Fetch and/or create the patient's medical record
                    MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(patientId);
                    if (medicalRecord == null) {
                        System.out.println("No medical record found for patient ID: " + patientId);
                        System.out.println("Creating a new medical record...");
                        medicalRecord = new MedicalRecord(
                                LocalDateTime.now(),
                                new Patient(patientId, "FirstName", "LastName", "PhoneNumber"),
                                new ArrayList<>()
                        );
                        medicalRecordService.addMedicalRecord(medicalRecord);
                        System.out.println("Medical record created successfully.");
                    }

                    // Add the consultation
                    System.out.println("Enter observation:");
                    String observationDesc = scanner.nextLine();
                    Observation observation = new Observation(observationDesc);

                    System.out.println("Enter prescription details (name, dosage, duration), or type 'none' to skip:");
                    String prescriptionInput = scanner.nextLine();
                    Prescription prescription = null;
                    if (!"none".equalsIgnoreCase(prescriptionInput)) {
                        String[] prescriptionDetails = prescriptionInput.split(",");
                        Medication medication = new Medication(
                                prescriptionDetails[0].trim(),
                                Integer.parseInt(prescriptionDetails[1].trim()),
                                Integer.parseInt(prescriptionDetails[2].trim())
                        );
                        prescription = new Prescription(LocalDateTime.now(), medicalRecord.getPatient(), List.of(medication));
                    }

                    // Add a summary
                    System.out.println("Enter summary of the consultation:");
                    String consultationSummary = scanner.nextLine();

                    // Check if the patient needs a medical certificate
                    System.out.println("Do you want a medical certificate? (yes/no)");
                    String certificateRequest = scanner.nextLine();
                    MedicalCertificate medicalCertificate = null;
                    if ("yes".equalsIgnoreCase(certificateRequest)) {
                        System.out.println("Enter the number of days for rest:");
                        int restDays = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the reason for the medical certificate:");
                        String certificateReason = scanner.nextLine();

                        // Create the medical certificate
                        medicalCertificate = new MedicalCertificate(
                                LocalDateTime.now(),  // The current date and time for the certificate
                                medicalRecord.getPatient(),
                                new Doctor(doctorId, "FirstName", "LastName", "Specialization"), // Replace with actual logic to get the doctor
                                new Summary(certificateReason),  // Create a summary with the certificate reason
                                restDays  // Number of rest days
                        );
                    }

                    // Create the consultation record
                    Consultation consultation = new Consultation(
                            LocalDateTime.now(),
                            medicalRecord.getPatient(),
                            new Doctor(doctorId, "FirstName", "LastName", "Specialization"), // Replace with actual logic
                            observation,
                            prescription,
                            medicalCertificate,  // Include the medical certificate if it exists
                            new Summary(consultationSummary)
                    );

                    medicalRecord.getConsultations().add(consultation);
                    medicalRecordService.updateRecord(medicalRecord);

                    System.out.println("Consultation recorded successfully.");
                }
                case 3 -> {
                    System.out.println("Returning to main menu...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void handleSecretary() {
        System.out.println("Welcome, Secretary!");
        System.out.println("Options:");
        System.out.println("1. Schedule an appointment");
        System.out.println("2. View patient records");
        System.out.println("3. Exit");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> {
                System.out.println("Enter patient ID:");
                String patientId = scanner.nextLine();
                System.out.println("Enter patient's first name:");
                String firstName = scanner.nextLine();
                System.out.println("Enter patient's last name:");
                String lastName = scanner.nextLine();
                System.out.println("Enter patient's phone number:");
                String phone = scanner.nextLine();
                System.out.println("Enter appointment date and time (yyyy-MM-ddTHH:mm):");
                LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());

                // Add doctor's details
                System.out.println("Enter doctor's ID:");
                String doctorId = scanner.nextLine();
                System.out.println("Enter doctor's first name:");
                String doctorFirstName = scanner.nextLine();
                System.out.println("Enter doctor's last name:");
                String doctorLastName = scanner.nextLine();
                System.out.println("Enter doctor's specialization:");
                String doctorSpecialization = scanner.nextLine();

                // Create patient and doctor objects
                Patient patient = new Patient(patientId, firstName, lastName, phone);
                Doctor doctor = new Doctor(doctorId, doctorFirstName, doctorLastName, doctorSpecialization);

                // Create patient file and add it to the patient file service
                // Initializing empty lists for MedicalHistory and Summary
                PatientFile patientFile = new PatientFile(patient, new ArrayList<>(), new ArrayList<>());
                patientFileService.addPatientFile(patientFile);

                // Create and save the appointment
                Secretary secretary = new Secretary("SecretaryFirstName", "SecretaryLastName"); // Replace with actual secretary details
                Appointment appointment = new Appointment(dateTime, patient, secretary, "Scheduled");
                appointment.setDoctor(doctor);

                appointmentService.addAppointment(appointment);
                System.out.println("Appointment scheduled successfully!");
            }
            case 2 -> {
                System.out.println("Enter patient ID:");
                String patientId = scanner.nextLine();
                PatientFile file = patientFileService.getPatientFile(patientId);
                if (file == null) {
                    System.out.println("No patient records found for ID: " + patientId);
                } else {
                    System.out.println(file.toString());
                }
            }
            case 3 -> System.out.println("Returning to main menu...");
            default -> System.out.println("Invalid option.");
        }
    }

    public void handlePatient() {
        System.out.println("Welcome, Patient!");
        System.out.println("Options:");
        System.out.println("1. View medical history");
        System.out.println("2. View appointments");
        System.out.println("3. View medical record");
        System.out.println("4. View patient file");
        System.out.println("5. Exit");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> {
                System.out.println("Enter your patient ID:");
                String patientId = scanner.nextLine();
                PatientFile file = patientFileService.getPatientFile(patientId);
                if (file == null) {
                    System.out.println("No medical history found for patient with ID: " + patientId);
                } else {
                    System.out.println("Medical History: " + (file.getMedicalHistory().isEmpty() ? "No history available" : file.getMedicalHistory()));
                }
            }
            case 2 -> {
                System.out.println("Enter your patient ID:");
                String patientId = scanner.nextLine();
                List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patientId);
                if (appointments.isEmpty()) {
                    System.out.println("No appointments found for patient with ID: " + patientId);
                } else {
                    appointments.forEach(appointment -> System.out.println(appointment.getRecordDetails()));
                }
            }
            case 3 -> {
                System.out.println("Enter your patient ID:");
                String patientId = scanner.nextLine();
                MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(patientId);
                if (medicalRecord == null) {
                    System.out.println("No medical record found for patient with ID: " + patientId);
                } else {
                    System.out.println("Medical Record Details:");
                    Patient patient = medicalRecord.getPatient();
                    System.out.println("Patient: " + patient.getDetails());
                    System.out.println(medicalRecord.getRecordDetails());
                }
            }
            case 4 -> {
                System.out.println("Enter your patient ID:");
                String patientId = scanner.nextLine();
                PatientFile patientFile = patientFileService.getPatientFile(patientId);
                if (patientFile == null) {
                    System.out.println("No patient file found for patient with ID: " + patientId);
                } else {
                    System.out.println("Patient File Details:");
                    System.out.println(patientFile.toString());
                }
            }
            case 5 -> System.out.println("Returning to main menu...");
            default -> System.out.println("Invalid option.");
        }
    }
}