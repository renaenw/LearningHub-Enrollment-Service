package edu.lms.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lms.service.entity.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByUsername(String username);

    List<Enrollment> findByCourseId(String courseId);

    Optional<Enrollment> findByCourseIdAndUsername(
            String courseId,
            String username
    );

    boolean existsByCourseIdAndUsername(
            String courseId,
            String username
    );
}