package com.school.dashboard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.dashboard.dto.StudentRequest;
import com.school.dashboard.model.Student;
import com.school.dashboard.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ✅ Register new student
    public Student registerStudent(StudentRequest request, MultipartFile image) throws IOException {
        if (studentRepository.existsByAdmissionNo(request.getAdmissionNo())) {
            throw new RuntimeException("Admission number already exists!");
        }

        Student student = new Student();
        mapRequestToStudent(student, request);

        // Handle image upload
        if (image != null && !image.isEmpty()) {
            String filePath = saveImage(image);
            student.setImagePath(filePath);
        }

        return studentRepository.save(student);
    }

    // ✅ Get student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // ✅ Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ✅ Get student image
    public byte[] getStudentImage(Long id) throws IOException {
        Student student = getStudentById(id);
        if (student.getImagePath() == null) {
            throw new RuntimeException("No image available for this student");
        }
        File file = new File(student.getImagePath());
        //return java.nio.file.Files.readAllBytes(file.toPath());
        return Files.readAllBytes(file.toPath());
    }
    
 // ✅ Detect image MIME type
    public String getImageContentType(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.probeContentType(path);
    }

    // ✅ Update student
    public Student updateStudent(Long id, StudentRequest request, MultipartFile image) throws IOException {
        Student student = getStudentById(id);

        mapRequestToStudent(student, request);

        // If new image uploaded, replace
        if (image != null && !image.isEmpty()) {
            String filePath = saveImage(image);
            student.setImagePath(filePath);
        }

        return studentRepository.save(student);
    }

    // ✅ Delete student
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);

        // Delete image if exists
        if (student.getImagePath() != null) {
            File file = new File(student.getImagePath());
            if (file.exists()) {
                file.delete();
            }
        }

        studentRepository.delete(student);
    }

    // --- Utility methods ---
    private void mapRequestToStudent(Student student, StudentRequest request) {
        student.setAdmissionNo(request.getAdmissionNo());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setGender(request.getGender());
        student.setAdmissionForClass(request.getAdmissionForClass());
        student.setSection(request.getSection());
        student.setFatherName(request.getFatherName());
        student.setFatherCellPhone(request.getFatherCellPhone());
        student.setMotherName(request.getMotherName());
        student.setMotherCellPhone(request.getMotherCellPhone());
        student.setDob(request.getDob());
        student.setReligion(request.getReligion());
        student.setAdmissionDate(request.getAdmissionDate());
        student.setSiblings(request.getSiblings());
        student.setUdise(request.getUdise());
        student.setAddress(request.getAddress());
        student.setAadharNumber(request.getAadharNumber());
        student.setMotherTongue(request.getMotherTongue());
        student.setFirstLanguage(request.getFirstLanguage());
        student.setSecondLanguage(request.getSecondLanguage());
    }

    private String saveImage(MultipartFile image) throws IOException {
    	// ✅ Store in user’s home directory → persists across restarts
    	String uploadDir = System.getProperty("user.home") + File.separator + "student_uploads";

        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // Create unique filename
        String filePath = uploadDir + File.separator + System.currentTimeMillis() + "_" + image.getOriginalFilename();

        // Save the file
        image.transferTo(new File(filePath));

        return filePath;
    }
    
    
    
    
    
    
    
    
    public List<Student> searchStudents(String admissionNo, String fatherPhone, String motherPhone, String name) {
        if (admissionNo != null) {
            return studentRepository.findByAdmissionNo(admissionNo);
        } else if (fatherPhone != null) {
            return studentRepository.findByFatherCellPhone(fatherPhone);
        } else if (motherPhone != null) {
            return studentRepository.findByMotherCellPhone(motherPhone);
        } else if (name != null) {
            return studentRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
        }
        return List.of(); // return empty if no filter provided
    }

    
    
    
    

}
