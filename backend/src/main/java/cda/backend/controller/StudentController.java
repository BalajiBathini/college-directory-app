package cda.backend.controller;

import cda.backend.model.StudentProfile;
import cda.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllStudents() {
        List<StudentProfile> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudentById(@PathVariable Long id) {
        StudentProfile studentProfile = studentService.getStudentById(id);
        return Optional.ofNullable(studentProfile)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentProfile> createStudent(@RequestBody StudentProfile studentProfile) {
        StudentProfile createdStudent = studentService.createStudent(studentProfile);
        return ResponseEntity.status(201).body(createdStudent);  // Return 201 Created status
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfile> updateStudent(@PathVariable Long id, @RequestBody StudentProfile studentProfile) {
        StudentProfile updatedStudent = studentService.updateStudent(id, studentProfile);
        return Optional.ofNullable(updatedStudent)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());  // Return 404 if not found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        boolean isDeleted = studentService.deleteStudent(id);
        return isDeleted
                ? ResponseEntity.ok("Student deleted successfully!")
                : ResponseEntity.status(404).body("Student not found!");  // Return 404 if not found
    }
}
