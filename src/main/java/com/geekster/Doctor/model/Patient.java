package com.geekster.Doctor.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_patient")
public class Patient {

    @Id
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "disease_type")
    private String diseaseType;

    @Column(name = "gender")
    private String gender;

    @Column(name = "admit_date")
    private Timestamp admitDate;

    @JoinColumn(name = "doctor_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(Timestamp admitDate) {
        this.admitDate = admitDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
