package cda.backend.controller;

import cda.backend.model.FacultyProfile;
import cda.backend.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<FacultyProfile>> getAllFaculty() {
        List<FacultyProfile> facultyProfiles = facultyService.getAllFaculty();
        return ResponseEntity.ok(facultyProfiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyProfile> getFacultyById(@PathVariable Long id) {
        FacultyProfile facultyProfile = facultyService.getFacultyById(id);
        return facultyProfile != null
                ? ResponseEntity.ok(facultyProfile)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
    }

    @PostMapping
    public ResponseEntity<FacultyProfile> createFaculty(@RequestBody FacultyProfile facultyProfile) {
        FacultyProfile createdProfile = facultyService.createFaculty(facultyProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyProfile> updateFaculty(@PathVariable Long id, @RequestBody FacultyProfile facultyProfile) {
        FacultyProfile updatedProfile = facultyService.updateFaculty(id, facultyProfile);
        return updatedProfile != null
                ? ResponseEntity.ok(updatedProfile)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long id) {
        if (facultyService.deleteFaculty(id)) {
            return ResponseEntity.ok("Faculty deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty not found!"); // 404 Not Found
        }
    }
}
