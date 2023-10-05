package vn.edu.iuh.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.dao.CandidateSkillDAO;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.entities.CandidateSkill;
import vn.edu.iuh.fit.entities.CandidateSkillID;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillLevel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class CandidateSkillTests {
    private final CandidateSkillDAO candidateSkillDAO;

    @Autowired
    CandidateSkillTests(CandidateSkillDAO candidateSkillDAO) {
        this.candidateSkillDAO = candidateSkillDAO;
    }

//    @Test
    void create() {
        UUID skillId = UUID.fromString("176b4526-99eb-4b94-8c9f-5522fc1592ca");
        UUID canId = UUID.fromString("03b6a664-fba6-4da9-8347-0acd52b2a89c");
        Skill skill = new Skill(skillId);
        Candidate candidate = new Candidate(canId);
        CandidateSkill candidateSkill = new CandidateSkill("More infos", SkillLevel.INTERMEDIATE, skill, candidate);

        candidateSkillDAO.create(candidateSkill);

        Optional<CandidateSkill> candidateSkillOptional = candidateSkillDAO.findById(new CandidateSkillID(skill, candidate));

        Assertions.assertTrue(candidateSkillOptional.isPresent());
    }

    @Test
    void getAll() {
        List<CandidateSkill> candidateSkills = candidateSkillDAO.getAll();

        Assertions.assertFalse(candidateSkills.isEmpty());
    }

    @Test
    void findById() {
        UUID skillId = UUID.fromString("176b4526-99eb-4b94-8c9f-5522fc1592ca");
        UUID canId = UUID.fromString("03b6a664-fba6-4da9-8347-0acd52b2a89c");
        Skill skill = new Skill(skillId);
        Candidate candidate = new Candidate(canId);

        Optional<CandidateSkill> candidateSkillOptional = candidateSkillDAO.findById(new CandidateSkillID(skill, candidate));

        Assertions.assertTrue(candidateSkillOptional.isPresent());
    }

    @Test
    void update() {
        UUID skillId = UUID.fromString("176b4526-99eb-4b94-8c9f-5522fc1592ca");
        UUID canId = UUID.fromString("03b6a664-fba6-4da9-8347-0acd52b2a89c");
        Skill skill = new Skill(skillId);
        Candidate candidate = new Candidate(canId);
        String newMoreInfos = "New more infos";

        CandidateSkill candidateSkill = new CandidateSkill(newMoreInfos, SkillLevel.INTERMEDIATE, skill, candidate);

        candidateSkillDAO.update(candidateSkill);

        Optional<CandidateSkill> candidateSkillOptional = candidateSkillDAO.findById(new CandidateSkillID(skill, candidate));

        Assertions.assertTrue(candidateSkillOptional.isPresent() && candidateSkillOptional.get().getMoreInfos().equals(newMoreInfos));
    }

    @Test
    void delete() {
        UUID skillId = UUID.fromString("176b4526-99eb-4b94-8c9f-5522fc1592ca");
        UUID canId = UUID.fromString("03b6a664-fba6-4da9-8347-0acd52b2a89c");
        Skill skill = new Skill(skillId);
        Candidate candidate = new Candidate(canId);

        CandidateSkillID candidateSkillID = new CandidateSkillID(skill, candidate);

        candidateSkillDAO.delete(candidateSkillID);

        Optional<CandidateSkill> candidateSkillOptional = candidateSkillDAO.findById(candidateSkillID);

        Assertions.assertTrue(candidateSkillOptional.isEmpty());
    }
}