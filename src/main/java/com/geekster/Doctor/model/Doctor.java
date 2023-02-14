package com.geekster.Doctor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_doctor")
public class Doctor {

    @Id
    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "experience")
    private String experience;

    @Column(name = "specialized_in")
    private String specializedIn;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSpecializedIn() {
        return specializedIn;
    }

    public void setSpecializedIn(String specializedIn) {
        this.specializedIn = specializedIn;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", experience='" + experience + '\'' +
                ", specializedIn='" + specializedIn + '\'' +
                '}';
    }
}
