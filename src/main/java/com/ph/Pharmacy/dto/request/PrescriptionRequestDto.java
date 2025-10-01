package com.ph.Pharmacy.dto.request;

import com.ph.Pharmacy.entity.DoctorDetails;
import com.ph.Pharmacy.entity.Medicine;
import com.ph.Pharmacy.entity.PatientDetails;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionRequestDto {
    private String id;
    private String patientName;
    private String doctorName;
    private LocalDate date;
    private String status;
    private PatientDetails patientDetails;
    private DoctorDetails doctorDetails;
    private List<Medicine> medicines;
    private String notes;
    private List<MultipartFile> images;

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
    public List<MultipartFile> getImages() { return images; }
    public void setImages(List<MultipartFile> images) { this.images = images; }
}
