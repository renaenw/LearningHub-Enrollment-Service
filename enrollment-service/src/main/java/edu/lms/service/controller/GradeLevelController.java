package edu.lms.service.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.lms.service.entity.GradeLevel;
import edu.lms.service.service.GradeLevelService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment/grade-levels")
public class GradeLevelController {

    private final GradeLevelService gradeLevelService;

    public GradeLevelController(GradeLevelService gradeLevelService) {
        this.gradeLevelService = gradeLevelService;
    }

    @GetMapping
    public List<GradeLevel> getAllGradeLevels() {
        return gradeLevelService.getAllGradeLevels();
    }

    @GetMapping("/{id}")
    public GradeLevel getGradeLevelById(
            @PathVariable String id) {

        return gradeLevelService.getGradeLevelById(id);
    }

    @PostMapping
    public ResponseEntity<GradeLevel> createGradeLevel(
            @Valid @RequestBody GradeLevel gradeLevel) {

        GradeLevel created =
                gradeLevelService.createGradeLevel(gradeLevel);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @PutMapping("/{id}")
    public GradeLevel updateGradeLevel(
            @PathVariable String id,
            @Valid @RequestBody GradeLevel gradeLevel) {

        return gradeLevelService.updateGradeLevel(
                id,
                gradeLevel
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGradeLevel(
            @PathVariable String id) {

        gradeLevelService.deleteGradeLevel(id);

        return ResponseEntity.noContent().build();
    }
}