package com.gn.pharmacy.dto.request;

public class StatusUpdateDto {
    private String newStatus;
    private String pharmacistNotes;

    public String getNewStatus() { return newStatus; }
    public void setNewStatus(String newStatus) { this.newStatus = newStatus; }
    public String getPharmacistNotes() { return pharmacistNotes; }
    public void setPharmacistNotes(String pharmacistNotes) { this.pharmacistNotes = pharmacistNotes; }
}
