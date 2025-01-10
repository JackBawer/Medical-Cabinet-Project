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
    private DoctorService doctorService;  // Add DoctorService as a field

    public UI(Scanner scanner, MedicalRecordService medicalRecordService,
              PatientFileService patientFileService, AppointmentService appointmentService, DoctorService doctorService) {
        this.scanner = scanner;
        this.medicalRecordService = medicalRecordService;
        this.patientFileService = patientFileService;
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;  // Initialize the DoctorService
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
    3. View a medical record
    4. View a patient file
    5. Exit
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

                    // Check if the doctor has a scheduled appointment with the patient
                    Appointment appointment = appointmentService.getScheduledAppointmentByPatient(patientId, doctorId);
                    if (appointment == null) {
                        System.out.println("You do not have a scheduled appointment with this patient.");
                        return;
                    }

                    // Fetch the actual doctor's details from the appointment
                    Doctor doctor = doctorService.getDoctorById(doctorId);
                    if (doctor == null) {
                        System.out.println("Doctor not found with ID: " + doctorId);
                        return;
                    }

                    // Fetch and/or create the patient's medical record
                    MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(patientId);
                    if (medicalRecord == null) {
                        System.out.println("No medical record found for patient ID: " + patientId);
                        System.out.println("Creating a new medical record...");
                        // Fetch patient details
                        PatientFile patientFile = patientFileService.getPatientFile(patientId);
                        Patient patient;
                        if (patientFile == null) {
                            System.out.println("No patient file found for patient ID: " + patientId);
                            System.out.println("Please enter patient's first name:");
                            String firstName = scanner.nextLine();
                            System.out.println("Please enter patient's last name:");
                            String lastName = scanner.nextLine();
                            System.out.println("Please enter patient's phone number:");
                            String phoneNumber = scanner.nextLine();
                            patient = new Patient(patientId, firstName, lastName, phoneNumber);
                        } else {
                            patient = patientFile.getPatient();
                        }
                        medicalRecord = new MedicalRecord(
                                LocalDateTime.now(),
                                patient,
                                new ArrayList<>()
                        );
                        medicalRecordService.addMedicalRecord(medicalRecord);
                        System.out.println("Medical record created successfully.");
                    }

                    // Add the consultation
                    System.out.println("Enter observation:");
                    String observationDesc = scanner.nextLine();
                    Observation observation = new Observation(observationDesc);

                    // Enter multiple medications
                    List<Medication> medications = new ArrayList<>();
                    while (true) {
                        System.out.println("Enter medication details (name, dosage, duration), or type 'done' to finish:");
                        String medicationInput = scanner.nextLine();
                        if ("done".equalsIgnoreCase(medicationInput)) {
                            break;
                        }
                        String[] medicationDetails = medicationInput.split(",");
                        Medication medication = new Medication(
                                medicationDetails[0].trim(),
                                Integer.parseInt(medicationDetails[1].trim()),
                                Integer.parseInt(medicationDetails[2].trim())
                        );
                        medications.add(medication);
                    }

                    Prescription prescription = null;
                    if (!medications.isEmpty()) {
                        prescription = new Prescription(LocalDateTime.now(), medicalRecord.getPatient(), medications);
                    }

                    // Add a summary
                    System.out.println("Enter summary of the consultation:");
                    String consultationSummary = scanner.nextLine();
                    Summary summary = new Summary(consultationSummary);

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
                                doctor,
                                new Summary(certificateReason),  // Create a summary with the certificate reason
                                restDays  // Number of rest days
                        );
                    }

                    // Create the consultation record
                    Consultation consultation = new Consultation(
                            LocalDateTime.now(),
                            medicalRecord.getPatient(),
                            doctor, // Use the actual doctor's details
                            observation,
                            prescription,
                            medicalCertificate,  // Include the medical certificate if it exists
                            summary
                    );

                    // Update the medical record and patient file
                    medicalRecord.getConsultations().add(consultation);
                    medicalRecordService.updateMedicalRecord(medicalRecord);

                    PatientFile patientFile = patientFileService.getPatientFile(patientId);
                    if (patientFile != null) {
                        patientFile.getSummaries().add(summary);
                        patientFileService.updatePatientFile(patientFile);
                    }

                    // Update the appointment status to completed
                    appointment.setStatus("Completed");
                    appointmentService.updateAppointment(appointment);

                    System.out.println("Consultation recorded successfully.");
                }
                case 3 -> {
                    System.out.println("Enter patient ID:");
                    String patientId = scanner.nextLine();
                    MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(patientId);
                    if (medicalRecord == null) {
                        System.out.println("No medical record found for patient ID: " + patientId);
                    } else {
                        System.out.println(medicalRecord.getRecordDetails());
                        System.out.println("Do you want to view detailed consultations for this record? (yes/no)");
                        String viewDetails = scanner.nextLine();
                        if ("yes".equalsIgnoreCase(viewDetails)) {
                            System.out.println(medicalRecord.getDetailedRecord());
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Enter patient ID:");
                    String patientId = scanner.nextLine();
                    PatientFile patientFile = patientFileService.getPatientFile(patientId);
                    if (patientFile == null) {
                        System.out.println("No patient file found for patient ID: " + patientId);
                    } else {
                        System.out.println(patientFile.toString());
                    }
                }
                case 5 -> {
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
        System.out.println("1. Add a new doctor");
        System.out.println("2. Schedule an appointment");
        System.out.println("3. View patient files");
        System.out.println("4. Exit");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> {
                // Add a new doctor
                System.out.println("Enter doctor's ID:");
                String doctorId = scanner.nextLine();
                System.out.println("Enter doctor's first name:");
                String doctorFirstName = scanner.nextLine();
                System.out.println("Enter doctor's last name:");
                String doctorLastName = scanner.nextLine();
                System.out.println("Enter doctor's specialization:");
                String doctorSpecialization = scanner.nextLine();

                Doctor doctor = new Doctor(doctorId, doctorFirstName, doctorLastName, doctorSpecialization);
                doctorService.addDoctor(doctor);
                System.out.println("Doctor added successfully!");
            }
            case 2 -> {
                // Schedule an appointment
                System.out.println("Enter patient ID:");
                String patientId = scanner.nextLine();
                System.out.println("Enter patient's first name:");
                String firstName = scanner.nextLine();
                System.out.println("Enter patient's last name:");
                String lastName = scanner.nextLine();
                System.out.println("Enter patient's phone number:");
                String phone = scanner.nextLine();
                System.out.println("Enter patient's medical history (e.g., allergies):");
                String medicalHistory = scanner.nextLine();
                System.out.println("Enter appointment date and time (yyyy-MM-ddTHH:mm):");
                LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());

                // Add doctor's details
                System.out.println("Enter doctor's ID:");
                String doctorId = scanner.nextLine();

                // Fetch the doctor by ID
                Doctor doctor = doctorService.getDoctorById(doctorId);
                if (doctor == null) {
                    System.out.println("Doctor not found with ID: " + doctorId);
                    return;
                }

                // Create patient and doctor objects
                Patient patient = new Patient(patientId, firstName, lastName, phone);

                // Create patient file and add it to the patient file service
                List<MedicalHistory> medicalHistories = new ArrayList<>();
                medicalHistories.add(new MedicalHistory(medicalHistory));
                PatientFile patientFile = new PatientFile(patient, medicalHistories, new ArrayList<>());
                patientFileService.addPatientFile(patientFile);

                // Create and save the appointment
                Secretary secretary = new Secretary("SecretaryFirstName", "SecretaryLastName"); // Replace with actual secretary details
                Appointment appointment = new Appointment(dateTime, patient, secretary, "Scheduled");
                appointment.setDoctor(doctor);

                appointmentService.addAppointment(appointment);
                System.out.println("Appointment scheduled successfully!");
            }
            case 3 -> {
                // View patient files
                System.out.println("Enter patient ID:");
                String patientId = scanner.nextLine();
                PatientFile file = patientFileService.getPatientFile(patientId);
                if (file == null) {
                    System.out.println("No patient records found for ID: " + patientId);
                } else {
                    System.out.println(file.toString());
                }
            }
            case 4 -> System.out.println("Returning to main menu...");
            default -> System.out.println("Invalid option.");
        }
    }

    public void handlePatient() {
        System.out.println("Welcome, Patient!");
        while (true) {
            System.out.println("Options:");
            System.out.println("""
        1. View medical history
        2. View appointments
        3. View medical record
        4. View patient file
        5. Exit
        """);

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
                        System.out.println("Do you want to view detailed consultations for this record? (yes/no)");
                        String viewDetails = scanner.nextLine();
                        if ("yes".equalsIgnoreCase(viewDetails)) {
                            System.out.println(medicalRecord.getDetailedRecord());
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Enter your patient ID:");
                    String patientId = scanner.nextLine();
                    PatientFile patientFile = patientFileService.getPatientFile(patientId);
                    if (patientFile == null) {
                        System.out.println("No patient file found for patient ID: " + patientId);
                    } else {
                        System.out.println("Patient File Details:");
                        System.out.println(patientFile.toString());
                    }
                }
                case 5 -> {
                    System.out.println("Returning to main menu...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}