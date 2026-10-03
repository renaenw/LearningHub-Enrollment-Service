package edu.lms.service.service;

import org.springframework.stereotype.Service;

import edu.lms.service.entity.GradeLevel;
import edu.lms.service.repository.GradeLevelRepository;

import java.util.List;

@Service
public class GradeLevelService {

    private final GradeLevelRepository gradeLevelRepository;

    public GradeLevelService(GradeLevelRepository gradeLevelRepository) {
        this.gradeLevelRepository = gradeLevelRepository;
    }

    public List<GradeLevel> getAllGradeLevels() {
        return gradeLevelRepository.findAll();
    }

    public GradeLevel getGradeLevelById(String id) {
        return gradeLevelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Grade level not found with id: " + id));
    }

    public GradeLevel createGradeLevel(GradeLevel gradeLevel) {
        return gradeLevelRepository.save(gradeLevel);
    }

    public GradeLevel updateGradeLevel(
            String id,
            GradeLevel updatedGradeLevel) {

        GradeLevel existing = getGradeLevelById(id);

        existing.setName(updatedGradeLevel.getName());

        return gradeLevelRepository.save(existing);
    }

    public void deleteGradeLevel(String id) {
        GradeLevel gradeLevel = getGradeLevelById(id);
        gradeLevelRepository.delete(gradeLevel);
    }
}