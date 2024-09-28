package cda.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "studentprofile")
public class StudentProfile {

    // Primary key for the entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One-to-One relationship with User entity
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String major;

    // Default constructor
    public StudentProfile() {}

    // Parameterized constructor
    public StudentProfile(User user, String firstName, String lastName, String email, String phoneNumber, String major) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.major = major;
    }
}
