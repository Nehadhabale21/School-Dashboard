package com.school.dashboard.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    private String gender;

    @Column(unique = true)
    private String employeeCode;

    private String fatherOrHusbandName;

    private String department;

    private LocalDate dateOfJoining;

    private String biometricId;

    private String designation;

    private String classTeacherFor;

    private String qualification;

    private String contactNumber;

    @Column(columnDefinition = "TEXT")
    private String address;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EducationalDetail> educationalDetails = new ArrayList<>();

    public Employee() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getEmployeeCode() { return employeeCode; }
    public void setEmployeeCode(String employeeCode) { this.employeeCode = employeeCode; }

    public String getFatherOrHusbandName() { return fatherOrHusbandName; }
    public void setFatherOrHusbandName(String fatherOrHusbandName) { this.fatherOrHusbandName = fatherOrHusbandName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    public String getBiometricId() { return biometricId; }
    public void setBiometricId(String biometricId) { this.biometricId = biometricId; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getClassTeacherFor() { return classTeacherFor; }
    public void setClassTeacherFor(String classTeacherFor) { this.classTeacherFor = classTeacherFor; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<EducationalDetail> getEducationalDetails() { return educationalDetails; }
    public void setEducationalDetails(List<EducationalDetail> educationalDetails) {
        this.educationalDetails.clear();
        if (educationalDetails != null) {
            educationalDetails.forEach(this::addEducationalDetail);
        }
    }

    public void addEducationalDetail(EducationalDetail ed) {
        ed.setEmployee(this);
        this.educationalDetails.add(ed);
    }

    public void removeEducationalDetail(EducationalDetail ed) {
        ed.setEmployee(null);
        this.educationalDetails.remove(ed);
    }
}
