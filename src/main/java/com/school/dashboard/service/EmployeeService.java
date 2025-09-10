package com.school.dashboard.service;

import com.school.dashboard.dto.EducationalDetailRequest;
import com.school.dashboard.dto.EmployeeRequest;
import com.school.dashboard.model.EducationalDetail;
import com.school.dashboard.model.Employee;
import com.school.dashboard.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee create(EmployeeRequest req) {
        Employee e = toEntity(req);
        return employeeRepository.save(e);
    }

    @Transactional
    public Employee update(Long id, EmployeeRequest req) {
        Employee existing = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setCategory(req.getCategory());
        existing.setName(req.getName());
        existing.setGender(req.getGender());
        existing.setEmployeeCode(req.getEmployeeCode());
        existing.setFatherOrHusbandName(req.getFatherOrHusbandName());
        existing.setDepartment(req.getDepartment());
        existing.setDateOfJoining(req.getDateOfJoining());
        existing.setBiometricId(req.getBiometricId());
        existing.setDesignation(req.getDesignation());
        existing.setClassTeacherFor(req.getClassTeacherFor());
        existing.setQualification(req.getQualification());
        existing.setContactNumber(req.getContactNumber());
        existing.setAddress(req.getAddress());

        existing.setEducationalDetails(
                req.getEducationalDetails() == null ? null : req.getEducationalDetails().stream().map(this::toEducationalDetail).collect(Collectors.toList())
        );

        return employeeRepository.save(existing);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name == null ? "" : name);
    }

    private Employee toEntity(EmployeeRequest req) {
        Employee e = new Employee();
        e.setCategory(req.getCategory());
        e.setName(req.getName());
        e.setGender(req.getGender());
        e.setEmployeeCode(req.getEmployeeCode());
        e.setFatherOrHusbandName(req.getFatherOrHusbandName());
        e.setDepartment(req.getDepartment());
        e.setDateOfJoining(req.getDateOfJoining());
        e.setBiometricId(req.getBiometricId());
        e.setDesignation(req.getDesignation());
        e.setClassTeacherFor(req.getClassTeacherFor());
        e.setQualification(req.getQualification());
        e.setContactNumber(req.getContactNumber());
        e.setAddress(req.getAddress());

        if (req.getEducationalDetails() != null) {
            List<EducationalDetail> eds = req.getEducationalDetails().stream().map(this::toEducationalDetail).collect(Collectors.toList());
            e.setEducationalDetails(eds);
        }

        return e;
    }

    private EducationalDetail toEducationalDetail(EducationalDetailRequest r) {
        EducationalDetail ed = new EducationalDetail();
        ed.setDegree(r.getDegree());
        ed.setInstitution(r.getInstitution());
        ed.setYearOfPassing(r.getYearOfPassing());
        ed.setGradeOrPercentage(r.getGradeOrPercentage());
        return ed;
    }
}
