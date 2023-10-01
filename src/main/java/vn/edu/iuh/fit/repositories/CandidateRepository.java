package vn.edu.iuh.fit.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.dao.AbstractDAO;
import vn.edu.iuh.fit.entities.Candidate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CandidateRepository extends AbstractDAO<Candidate, UUID> {
    public CandidateRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public boolean create(Candidate candidate) {
        String sql = "INSERT candidate VALUES (?,?,?,?,?,?)";

        int update = jdbcTemplate.update(sql, candidate.getId(), candidate.getDob(), candidate.getEmail(), candidate.getFullName(), candidate.getPhone(), candidate.getAddress().getId());

        return update > 0;
    }

    @Override
    public List<Candidate> getAll() {
        String sql = "Select * from candidate";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Candidate.class));
    }

    @Override
    public Optional<Candidate> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean update(Candidate candidate) {
        return false;
    }

    @Override
    public boolean delete(UUID uuid) {
        return false;
    }
}
