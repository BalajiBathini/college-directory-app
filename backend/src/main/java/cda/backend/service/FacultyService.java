package cda.backend.service;

import cda.backend.model.FacultyProfile;
import cda.backend.repository.FacultyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    public List<FacultyProfile> getAllFaculty() {
        return facultyProfileRepository.findAll();
    }

    public FacultyProfile getFacultyById(Long id) {
        return facultyProfileRepository.findById(id).orElse(null);
    }

    public FacultyProfile createFaculty(FacultyProfile facultyProfile) {
        return facultyProfileRepository.save(facultyProfile);
    }

    public FacultyProfile updateFaculty(Long id, FacultyProfile facultyProfile) {
        // Check if the faculty profile exists before updating
        Optional<FacultyProfile> existingProfile = facultyProfileRepository.findById(id);
        if (existingProfile.isPresent()) {
            facultyProfile.setId(id);  // Ensure the ID is set for the update
            return facultyProfileRepository.save(facultyProfile);
        } else {
            return null;  // or throw an exception
        }
    }

    public boolean deleteFaculty(Long id) {
        if (facultyProfileRepository.existsById(id)) {
            facultyProfileRepository.deleteById(id);
            return true;  // Return true if deletion was successful
        }
        return false;  // Return false if the faculty profile was not found
    }
}
