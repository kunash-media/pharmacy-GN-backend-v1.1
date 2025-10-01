package com.ph.Pharmacy.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prescriptions")
public class PrescriptionEntity {
    @Id
    private String id;
    private String patientName;
    private String doctorName;
    private LocalDate date;
    private String status;

    @Embedded
    private PatientDetails patientDetails;

    @Embedded
    private DoctorDetails doctorDetails;

    @ElementCollection
    private List<Medicine> medicines;

    private String notes;

    @ElementCollection
    @Lob
    private List<byte[]> images;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public PatientDetails getPatientDetails() { return patientDetails; }
    public void setPatientDetails(PatientDetails patientDetails) { this.patientDetails = patientDetails; }
    public DoctorDetails getDoctorDetails() { return doctorDetails; }
    public void setDoctorDetails(DoctorDetails doctorDetails) { this.doctorDetails = doctorDetails; }
    public List<Medicine> getMedicines() { return medicines; }
    public void setMedicines(List<Medicine> medicines) { this.medicines = medicines; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public List<byte[]> getImages() { return images; }
    public void setImages(List<byte[]> images) { this.images = images; }
}

