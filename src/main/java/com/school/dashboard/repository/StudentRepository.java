package com.school.dashboard.repository;

import com.school.dashboard.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByAdmissionNo(String admissionNo);

    
    
    List<Student> findByAdmissionNo(String admissionNo);

    List<Student> findByFatherCellPhone(String fatherCellPhone);

    List<Student> findByMotherCellPhone(String motherCellPhone);

    List<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}
