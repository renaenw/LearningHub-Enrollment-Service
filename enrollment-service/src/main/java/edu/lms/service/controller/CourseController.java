package edu.lms.service.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.lms.service.entity.Course;
import edu.lms.service.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(
            @Valid @RequestBody Course course) {

        Course created = courseService.createCourse(course);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable String id,
            @Valid @RequestBody Course course) {

        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable String id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/term/{termId}")
    public List<Course> getCoursesByTerm(
            @PathVariable String termId) {

        return courseService.getCoursesByTerm(termId);
    }

    @GetMapping("/grade-level/{gradeLevel}")
    public List<Course> getCoursesByGradeLevel(
            @PathVariable String gradeLevel) {

        return courseService.getCoursesByGradeLevel(gradeLevel);
    }

    @GetMapping("/teacher/{teacherUsername}")
    public List<Course> getCoursesByTeacher(
            @PathVariable String teacherUsername) {

        return courseService.getCoursesByTeacher(teacherUsername);
    }
}