package com.school.dashboard.controller;

import com.school.dashboard.dto.EmployeeRequest;
import com.school.dashboard.model.Employee;
import com.school.dashboard.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> create(@RequestBody EmployeeRequest req) {
        Employee created = employeeService.create(req);
        return ResponseEntity.created(URI.create("/api/employees/" + created.getId())).body(created);
    }
    
    @GetMapping("/allEmployees")
    public ResponseEntity<List<Employee>> all() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody EmployeeRequest req) {
        Employee updated = employeeService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> search(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(employeeService.searchByName(name));
    }
}
