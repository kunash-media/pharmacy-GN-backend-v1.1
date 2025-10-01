package com.ph.Pharmacy.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class PatientDetails {
    private int age;
    private String contact;
    private String email;
    private String address;

    // Getters and Setters
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
