package vn.edu.iuh.fit.services;

import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.dao.CandidateDAO;

import java.util.List;

@Component
public class CandidateServices {
    private final CandidateDAO candidateDAO;

    public CandidateServices(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO;
    }

    public List<Candidate> getAll() {
        return candidateDAO.getAll();
    }

    public boolean create(Candidate candidate) {
        return candidateDAO.create(candidate);
    }
}
