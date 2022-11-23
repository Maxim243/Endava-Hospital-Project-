package org.main.services;

import lombok.RequiredArgsConstructor;
import org.main.entities.Doctor;
import org.main.entities.Record;
import org.main.repository.DoctorRepository;
import org.main.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long doctorId){
        return doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
}
