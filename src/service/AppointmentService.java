package service;

import model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
	private List<Appointment> appointments = new ArrayList<>();
	
	public void scheduleAppointment(Appointment appointment) {
		appointments.add(appointment);
	}
	
	public List<Appointment> getAllAppointments() {
		return appointments;
	}
}
