package edu.lms.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lms.service.entity.GradeLevel;

public interface GradeLevelRepository
        extends JpaRepository<GradeLevel, String> {
}