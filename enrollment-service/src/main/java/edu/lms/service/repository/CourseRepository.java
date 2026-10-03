package edu.lms.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lms.service.entity.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findByTermId(String termId);

    List<Course> findByGradeLevel(String gradeLevel);

    List<Course> findByTeacherUsername(String teacherUsername);
}