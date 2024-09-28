package cda.backend.repository;



import cda.backend.model.FacultyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
}
