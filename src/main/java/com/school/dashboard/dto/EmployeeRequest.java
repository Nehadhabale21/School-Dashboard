package com.school.dashboard.dto;

import java.time.LocalDate;
import java.util.List;

public class EmployeeRequest {
    private String category;
    private String name;
    private String gender;
    private String employeeCode;
    private String fatherOrHusbandName;
    private String department;
    private LocalDate dateOfJoining;
    private String biometricId;
    private String designation;
    private String classTeacherFor;
    private String qualification;
    private String contactNumber;
    private String address;

    private List<EducationalDetailRequest> educationalDetails;

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
    public List<EducationalDetailRequest> getEducationalDetails() { return educationalDetails; }
    public void setEducationalDetails(List<EducationalDetailRequest> educationalDetails) { this.educationalDetails = educationalDetails; }
}
