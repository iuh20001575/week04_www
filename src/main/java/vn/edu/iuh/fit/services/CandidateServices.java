package vn.edu.iuh.fit.services;

import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.repositories.CandidateRepository;

import java.util.List;

@Component
public class CandidateServices {
    private final CandidateRepository candidateRepository;

    public CandidateServices(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getAll() {
        return candidateRepository.getAll();
    }

    public boolean create(Candidate candidate) {
        return candidateRepository.create(candidate);
    }
}
