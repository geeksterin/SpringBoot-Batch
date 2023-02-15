package com.geekster.Doctor.service;

import com.geekster.Doctor.dao.PatientRepository;
import com.geekster.Doctor.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository repository;

    public void savePatient(Patient patient) {
        repository.save(patient);
    }

}
