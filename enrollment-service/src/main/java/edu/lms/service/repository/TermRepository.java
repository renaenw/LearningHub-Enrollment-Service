package edu.lms.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lms.service.entity.Term;

import java.util.Optional;

public interface TermRepository extends JpaRepository<Term, String> {

    Optional<Term> findByIsCurrentTrue();
}