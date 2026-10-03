package edu.lms.service.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.lms.service.entity.Term;
import edu.lms.service.service.TermService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment/terms")
public class TermController {

    private final TermService termService;

    public TermController(TermService termService) {
        this.termService = termService;
    }

    @GetMapping
    public List<Term> getAllTerms() {
        return termService.getAllTerms();
    }

    @GetMapping("/{id}")
    public Term getTermById(@PathVariable String id) {
        return termService.getTermById(id);
    }

    @GetMapping("/current")
    public Term getCurrentTerm() {
        return termService.getCurrentTerm();
    }

    @PostMapping
    public ResponseEntity<Term> createTerm(
            @Valid @RequestBody Term term) {

        Term created = termService.createTerm(term);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @PutMapping("/{id}")
    public Term updateTerm(
            @PathVariable String id,
            @Valid @RequestBody Term term) {

        return termService.updateTerm(id, term);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerm(
            @PathVariable String id) {

        termService.deleteTerm(id);

        return ResponseEntity.noContent().build();
    }
}