package vn.edu.iuh.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import vn.edu.iuh.fit.dao.AddressDAO;
import vn.edu.iuh.fit.dao.CandidateDAO;
import vn.edu.iuh.fit.entities.Address;
import vn.edu.iuh.fit.entities.Candidate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ContextConfiguration(classes = WwwWeek03Application.class)
class CandidateTests {
    private final CandidateDAO candidateDAO;
    private final AddressDAO addressDAO;

    @Autowired
    CandidateTests(CandidateDAO candidateDAO, AddressDAO addressDAO) {
        this.candidateDAO = candidateDAO;
        this.addressDAO = addressDAO;
    }

    @Test
    void create() {
        Candidate candidate;
        int count = 0;
        int length = 10;
        for (int i = 1; i <= length; ++i) {
            UUID uuid = UUID.randomUUID();
            Address address = new Address(UUID.randomUUID(), "Street #" + i, "City #" + i, (short) i, "#" + i, "Code" + i);
            addressDAO.create(address);
            candidate = new Candidate(uuid, LocalDate.now(), String.format("emailTest%s@gmail.com", i), "Ho Ten #" + i, "098755431" + i, address);
            candidateDAO.create(candidate);

            Optional<Candidate> candidateOptional = candidateDAO.findById(uuid);
            if (candidateOptional.isPresent())
                count++;
        }

        Assertions.assertEquals(count, length);
    }

    @Test
    void getAll() {
        List<Candidate> all = candidateDAO.getAll();

        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    void findSuccessById() {
        Optional<Candidate> candidateOptional = candidateDAO.findById(UUID.fromString("7a791144-1ae3-4a84-96dd-59a889d96e3c"));

        Assertions.assertTrue(candidateOptional.isPresent());
    }

    @Test
    void findFailById() {
        Optional<Candidate> candidateOptional = candidateDAO.findById(UUID.fromString("7a791144-4567-4a84-96dd-59a889d96e3c"));

        Assertions.assertFalse(candidateOptional.isPresent());
    }

    //    @Test
    void update() {
        Optional<Candidate> candidateOptional = candidateDAO.findById(UUID.fromString("7a791144-1ae3-4a84-96dd-59a889d96e3c"));

        if (candidateOptional.isEmpty())
            Assertions.fail();

        Candidate candidate = candidateOptional.get();
        String newEmail = "newemail1@gmail.com";

        candidate.setEmail(newEmail);

        if (!candidateDAO.update(candidate))
            Assertions.fail();

        Optional<Candidate> find = candidateDAO.findById(UUID.fromString("7a791144-1ae3-4a84-96dd-59a889d96e3c"));

        Assertions.assertTrue(find.isPresent() && find.get().getEmail().equals(newEmail));
    }

    //    @Test
    void deleteSuccess() {
        Assertions.assertTrue(candidateDAO.delete(UUID.fromString("0c1e95c4-b0f1-4665-a537-dc86986e6e86")));
    }

    @Test
    void deleteFail() {
        Assertions.assertFalse(candidateDAO.delete(UUID.randomUUID()));
    }
}