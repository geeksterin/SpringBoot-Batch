package com.geekster.Doctor.dao;

import com.geekster.Doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
