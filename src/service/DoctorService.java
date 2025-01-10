package service;

import module.Doctor;
import java.util.HashMap;
import java.util.Map;

public class DoctorService {
    private Map<String, Doctor> doctors;

    public DoctorService() {
        this.doctors = new HashMap<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.put(doctor.getDoctorId(), doctor);
    }

    public Doctor getDoctorById(String doctorId) {
        return doctors.get(doctorId);
    }
}