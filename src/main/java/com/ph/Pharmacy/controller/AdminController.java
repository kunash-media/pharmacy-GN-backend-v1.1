package com.ph.Pharmacy.controller;

import com.ph.Pharmacy.dto.request.AdminRequestDto;
import com.ph.Pharmacy.dto.response.AdminResponseDto;
import com.ph.Pharmacy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create-Admin")
    public ResponseEntity<AdminResponseDto> createAdmin(@RequestBody AdminRequestDto adminRequestDto) {
        AdminResponseDto responseDto = adminService.createAdmin(adminRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDto> getAdminById(@PathVariable Long id) {
        AdminResponseDto responseDto = adminService.getAdminById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AdminResponseDto> getAdminByEmail(@PathVariable String email) {
        AdminResponseDto responseDto = adminService.getAdminByEmail(email);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<AdminResponseDto> getAdminByPhoneNumber(@PathVariable String phoneNumber) {
        AdminResponseDto responseDto = adminService.getAdminByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/get-All-Admins")
    public ResponseEntity<List<AdminResponseDto>> getAllAdmins() {
        List<AdminResponseDto> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/first-name/{firstName}")
    public ResponseEntity<List<AdminResponseDto>> getAdminsByFirstName(@PathVariable String firstName) {
        List<AdminResponseDto> admins = adminService.getAdminsByFirstName(firstName);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/last-name/{lastName}")
    public ResponseEntity<List<AdminResponseDto>> getAdminsByLastName(@PathVariable String lastName) {
        List<AdminResponseDto> admins = adminService.getAdminsByLastName(lastName);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/full-name/{firstName}/{lastName}")
    public ResponseEntity<List<AdminResponseDto>> getAdminsByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        List<AdminResponseDto> admins = adminService.getAdminsByFullName(firstName, lastName);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/email-domain/{domain}")
    public ResponseEntity<List<AdminResponseDto>> getAdminsByEmailDomain(@PathVariable String domain) {
        List<AdminResponseDto> admins = adminService.getAdminsByEmailDomain(domain);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/phone-pattern/{pattern}")
    public ResponseEntity<List<AdminResponseDto>> getAdminsByPhonePattern(@PathVariable String pattern) {
        List<AdminResponseDto> admins = adminService.getAdminsByPhonePattern(pattern);
        return ResponseEntity.ok(admins);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponseDto> updateAdmin(@PathVariable Long id, @RequestBody AdminRequestDto adminRequestDto) {
        AdminResponseDto responseDto = adminService.updateAdmin(id, adminRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdminResponseDto> updateAdminPartial(@PathVariable Long id, @RequestBody AdminRequestDto adminRequestDto) {
        AdminResponseDto responseDto = adminService.updateAdminPartial(id, adminRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        boolean exists = adminService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/phone/{phoneNumber}")
    public ResponseEntity<Boolean> existsByPhoneNumber(@PathVariable String phoneNumber) {
        boolean exists = adminService.existsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(exists);
    }
}

