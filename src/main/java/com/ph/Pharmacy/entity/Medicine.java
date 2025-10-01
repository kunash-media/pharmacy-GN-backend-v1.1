package com.ph.Pharmacy.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Medicine {
    private String drugName;
    private String dosage;
    private int quantity;
    private String duration;

    // Getters and Setters
    public String getDrugName() { return drugName; }
    public void setDrugName(String drugName) { this.drugName = drugName; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
}
