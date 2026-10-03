package edu.lms.service.service;

import org.springframework.stereotype.Service;

import edu.lms.service.entity.Term;
import edu.lms.service.repository.TermRepository;

import java.util.List;

@Service
public class TermService {

    private final TermRepository termRepository;

    public TermService(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    public List<Term> getAllTerms() {
        return termRepository.findAll();
    }

    public Term getTermById(String id) {
        return termRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Term not found with id: " + id));
    }

    public Term getCurrentTerm() {
        return termRepository.findByIsCurrentTrue()
                .orElseThrow(() ->
                        new RuntimeException("No current term found"));
    }

    public Term createTerm(Term term) {
        return termRepository.save(term);
    }

    public Term updateTerm(String id, Term updatedTerm) {

        Term existing = getTermById(id);

        existing.setName(updatedTerm.getName());
        existing.setStartDate(updatedTerm.getStartDate());
        existing.setEndDate(updatedTerm.getEndDate());
        existing.setCurrent(updatedTerm.isCurrent());
        existing.setMaxCredits(updatedTerm.getMaxCredits());
        existing.setQuarter(updatedTerm.getQuarter());
        existing.setAcademicYear(updatedTerm.getAcademicYear());

        return termRepository.save(existing);
    }

    public void deleteTerm(String id) {
        Term term = getTermById(id);
        termRepository.delete(term);
    }
}