package cda.backend.service;

import cda.backend.model.StudentProfile;
import cda.backend.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    public StudentProfile getStudentById(Long id) {
        return studentProfileRepository.findById(id).orElse(null);
    }

    public StudentProfile createStudent(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }

    public StudentProfile updateStudent(Long id, StudentProfile studentProfile) {
        // Check if the student exists
        if (!studentProfileRepository.existsById(id)) {
            return null; // or throw an exception, depending on your design
        }
        studentProfile.setId(id);  // Ensure the ID is set for the update
        return studentProfileRepository.save(studentProfile);
    }

    public boolean deleteStudent(Long id) {
        // Check if the student exists before attempting to delete
        if (studentProfileRepository.existsById(id)) {
            studentProfileRepository.deleteById(id);
            return true; // Indicate that deletion was successful
        }
        return false; // Indicate that the student was not found
    }
}
