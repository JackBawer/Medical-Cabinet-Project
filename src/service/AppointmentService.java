package service;

import module.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private List<Appointment> appointments;

    public AppointmentService() {
        this.appointments = new ArrayList<>();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public Appointment getAppointment(LocalDateTime dateTime, String patientId) {
        for (Appointment appointment : appointments) {
            if (appointment.getDateTime().equals(dateTime) &&
                    appointment.getPatient().getId().equals(patientId)) {
                return appointment;
            }
        }
        System.out.println("No appointment found for patient with ID: " + patientId + " on " + dateTime);
        return null;
    }

    public void updateAppointment(Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment existingAppointment = appointments.get(i);
            if (existingAppointment.getDateTime().equals(updatedAppointment.getDateTime()) &&
                    existingAppointment.getPatient().getId().equals(updatedAppointment.getPatient().getId())) {
                appointments.set(i, updatedAppointment);
                return;
            }
        }
        System.out.println("No appointment found for patient with ID: " + updatedAppointment.getPatient().getId() +
                " on " + updatedAppointment.getDateTime());
    }

    public void cancelAppointment(LocalDateTime dateTime, String patientId) {
        appointments.removeIf(appointment -> appointment.getDateTime().equals(dateTime) &&
                appointment.getPatient().getId().equals(patientId));
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor() != null && appointment.getDoctor().getDoctorId().equals(doctorId)) {
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId().equals(patientId)) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }
}
