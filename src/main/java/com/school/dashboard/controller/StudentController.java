package com.school.dashboard.controller;

import com.school.dashboard.dto.StudentRequest;
import com.school.dashboard.model.Student;
import com.school.dashboard.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ✅ Register new student with optional image
    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity<Student> registerStudent(
            @RequestPart("student") StudentRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        Student student = studentService.registerStudent(request, image);
        return ResponseEntity.ok(student);
    }

    // ✅ Get single student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // ✅ Get all students
    @GetMapping("/allStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // ✅ Get student image
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getStudentImage(@PathVariable Long id) throws IOException {
        Student student = studentService.getStudentById(id);
        byte[] imageData = studentService.getStudentImage(id);

        String contentType = studentService.getImageContentType(student.getImagePath());
        if (contentType == null) {
            contentType = "application/octet-stream"; // fallback
        }

        return ResponseEntity
                .ok()
                .header("Content-Type", contentType)
                .body(imageData);
    }

    // ✅ Update student (with optional image)
    @PutMapping(value = "/update/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestPart("student") StudentRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        Student updatedStudent = studentService.updateStudent(id, request, image);
        return ResponseEntity.ok(updatedStudent);
    }

    // ✅ Delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
    
    
    
   
    
    
    
 // ✅ Search students by admissionNo, fatherPhone, motherPhone, or name
    @GetMapping("/search")
    public ResponseEntity<?> searchStudents(
            @RequestParam(required = false) String admissionNo,
            @RequestParam(required = false) String fatherPhone,
            @RequestParam(required = false) String motherPhone,
            @RequestParam(required = false) String name) {

        List<Student> results = studentService.searchStudents(admissionNo, fatherPhone, motherPhone, name);

        if (results.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body("No students found...");
        }

        return ResponseEntity.ok(results);
    }

    
    
    
}
