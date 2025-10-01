package com.ph.Pharmacy.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class DoctorDetails {
    private String registrationNo;
    private String specialization;
    private String clinic;

    // Getters and Setters
    public String getRegistrationNo() { return registrationNo; }
    public void setRegistrationNo(String registrationNo) { this.registrationNo = registrationNo; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getClinic() { return clinic; }
    public void setClinic(String clinic) { this.clinic = clinic; }
}
