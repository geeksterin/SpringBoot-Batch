package com.geekster.Doctor.dao;

import com.geekster.Doctor.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
