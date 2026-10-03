package edu.lms.service.service;

import org.springframework.stereotype.Service;

import edu.lms.service.entity.Enrollment;
import edu.lms.service.repository.EnrollmentRepository;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // Get every enrollment
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // Get one enrollment by its ID
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Enrollment not found with id: " + id));
    }

    // Get all enrollments belonging to a student
    public List<Enrollment> getEnrollmentsByUsername(String username) {
        return enrollmentRepository.findByUsername(username);
    }

    // Get all students enrolled in a course
    public List<Enrollment> getEnrollmentsByCourseId(String courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    // Enroll a student in a course
    public Enrollment enrollStudent(Enrollment enrollment) {

        if (enrollmentRepository.existsByCourseIdAndUsername(
                enrollment.getCourseId(),
                enrollment.getUsername())) {

            throw new RuntimeException(
                    "Student is already enrolled in this course");
        }

        return enrollmentRepository.save(enrollment);
    }

    // Update an existing enrollment
    public Enrollment updateEnrollment(Long id, Enrollment updatedEnrollment) {

        Enrollment existing = getEnrollmentById(id);

        existing.setCourseId(updatedEnrollment.getCourseId());
        existing.setUsername(updatedEnrollment.getUsername());
        existing.setStatus(updatedEnrollment.getStatus());
        existing.setEnrolledAt(updatedEnrollment.getEnrolledAt());

        return enrollmentRepository.save(existing);
    }

    // Drop a student from a course
    public void dropStudent(String courseId, String username) {

        Enrollment enrollment =
                enrollmentRepository
                        .findByCourseIdAndUsername(courseId, username)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Enrollment not found"));

        enrollmentRepository.delete(enrollment);
    }

    // Delete directly by enrollment ID
    public void deleteEnrollment(Long id) {

        Enrollment enrollment = getEnrollmentById(id);

        enrollmentRepository.delete(enrollment);
    }
}