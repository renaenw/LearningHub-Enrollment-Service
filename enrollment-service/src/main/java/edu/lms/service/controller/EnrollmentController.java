package edu.lms.service.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.lms.service.entity.Enrollment;
import edu.lms.service.service.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // Get all enrollments
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // Get one enrollment
    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    // Create a new enrollment
    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(
            @Valid @RequestBody Enrollment enrollment) {

        Enrollment created =
                enrollmentService.enrollStudent(enrollment);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    // Update an enrollment
    @PutMapping("/{id}")
    public Enrollment updateEnrollment(
            @PathVariable Long id,
            @Valid @RequestBody Enrollment enrollment) {

        return enrollmentService.updateEnrollment(id, enrollment);
    }

    // Delete an enrollment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return ResponseEntity.noContent().build();
    }

    // Get enrollments for one student
    @GetMapping("/student/{username}")
    public List<Enrollment> getStudentEnrollments(
            @PathVariable String username) {

        return enrollmentService.getEnrollmentsByUsername(username);
    }

    // Get the roster for one course
    @GetMapping("/course/{courseId}")
    public List<Enrollment> getCourseEnrollments(
            @PathVariable String courseId) {

        return enrollmentService.getEnrollmentsByCourseId(courseId);
    }

    // Drop a student from a course
    @DeleteMapping("/course/{courseId}/student/{username}")
    public ResponseEntity<Void> dropStudent(
            @PathVariable String courseId,
            @PathVariable String username) {

        enrollmentService.dropStudent(courseId, username);

        return ResponseEntity.noContent().build();
    }
}