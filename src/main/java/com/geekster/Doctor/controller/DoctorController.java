package com.geekster.Doctor.controller;

import com.geekster.Doctor.model.Doctor;
import com.geekster.Doctor.service.DoctorService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {


    @Autowired
    DoctorService service;

    @PostMapping(value = "/doctor")
    public Doctor saveDoctor(@RequestBody Doctor doctor) {
        return service.saveDoctor(doctor);
    }

    @GetMapping(value = "/doctor")
    public List<Doctor> getDoctor(@Nullable @RequestParam String doctorId) {
        return service.getDoctor(doctorId);
    }





}
