package com.school.dashboard.repository;

import com.school.dashboard.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmployeeCode(String employeeCode);
    List<Employee> findByNameContainingIgnoreCase(String name);
}
