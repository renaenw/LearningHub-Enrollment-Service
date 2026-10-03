package edu.lms.service.service;

import org.springframework.stereotype.Service;

import edu.lms.service.entity.Course;
import edu.lms.service.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Course not found with id: " + id));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(String id, Course updatedCourse) {

        Course existing = getCourseById(id);

        existing.setTermId(updatedCourse.getTermId());
        existing.setName(updatedCourse.getName());
        existing.setGradeLevel(updatedCourse.getGradeLevel());
        existing.setTeacher(updatedCourse.getTeacher());
        existing.setColor(updatedCourse.getColor());
        existing.setDays(updatedCourse.getDays());
        existing.setStartTime(updatedCourse.getStartTime());
        existing.setEndTime(updatedCourse.getEndTime());
        existing.setCredits(updatedCourse.getCredits());
        existing.setStartDate(updatedCourse.getStartDate());
        existing.setEndDate(updatedCourse.getEndDate());
        existing.setTeacherUsername(updatedCourse.getTeacherUsername());

        return courseRepository.save(existing);
    }

    public void deleteCourse(String id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }

    public List<Course> getCoursesByTerm(String termId) {
        return courseRepository.findByTermId(termId);
    }

    public List<Course> getCoursesByGradeLevel(String gradeLevel) {
        return courseRepository.findByGradeLevel(gradeLevel);
    }

    public List<Course> getCoursesByTeacher(String teacherUsername) {
        return courseRepository.findByTeacherUsername(teacherUsername);
    }
}