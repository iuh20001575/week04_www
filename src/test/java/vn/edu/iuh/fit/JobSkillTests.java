package vn.edu.iuh.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.dao.JobSkillDAO;
import vn.edu.iuh.fit.entities.Job;
import vn.edu.iuh.fit.entities.JobSkill;
import vn.edu.iuh.fit.entities.JobSkillID;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillLevel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class JobSkillTests {
    private final JobSkillDAO jobSkillDAO;

    @Autowired
    JobSkillTests(JobSkillDAO jobSkillDAO) {
        this.jobSkillDAO = jobSkillDAO;
    }

//    @Test
    void create() {
        UUID skillId = UUID.fromString("176b4526-99eb-4b94-8c9f-5522fc1592ca");
        UUID jobId = UUID.fromString("bb1679a2-be16-487a-b880-7ab76fa6c4e0");
        Skill skill = new Skill(skillId);
        Job job = new Job(jobId);
        JobSkill jobSkill = new JobSkill("More infos", SkillLevel.INTERMEDIATE, job, skill);

        jobSkillDAO.create(jobSkill);

        Optional<JobSkill> jobSkillOptional = jobSkillDAO.findById(new JobSkillID(job, skill));

        Assertions.assertTrue(jobSkillOptional.isPresent());
    }

    @Test
    void getAll() {
        List<JobSkill> jobSkills = jobSkillDAO.getAll();

        Assertions.assertFalse(jobSkills.isEmpty());
    }

    @Test
    void findById() {
        UUID skillId = UUID.fromString("176b4526-99eb-4b94-8c9f-5522fc1592ca");
        UUID jobId = UUID.fromString("bb1679a2-be16-487a-b880-7ab76fa6c4e0");
        Skill skill = new Skill(skillId);
        Job job = new Job(jobId);

        Optional<JobSkill> jobSkillOptional = jobSkillDAO.findById(new JobSkillID(job, skill));

        Assertions.assertTrue(jobSkillOptional.isPresent());
    }

    @Test
    void update() {
        UUID skillId = UUID.fromString("176b4526-99eb-4b94-8c9f-5522fc1592ca");
        UUID jobId = UUID.fromString("bb1679a2-be16-487a-b880-7ab76fa6c4e0");
        Skill skill = new Skill(skillId);
        Job job = new Job(jobId);
        String newMoreInfos = "New more infos";

        JobSkill jobSkill = new JobSkill(newMoreInfos, SkillLevel.INTERMEDIATE, job, skill);

        jobSkillDAO.update(jobSkill);

        Optional<JobSkill> jobSkillOptional = jobSkillDAO.findById(new JobSkillID(job, skill));

        Assertions.assertTrue(jobSkillOptional.isPresent() && jobSkillOptional.get().getMoreInfos().equals(newMoreInfos));
    }

    @Test
    void delete() {
        UUID skillId = UUID.fromString("176b4526-99eb-4b94-8c9f-5522fc1592ca");
        UUID jobId = UUID.fromString("bb1679a2-be16-487a-b880-7ab76fa6c4e0");
        Skill skill = new Skill(skillId);
        Job job = new Job(jobId);

        JobSkillID jobSkillID = new JobSkillID(job,skill);

        jobSkillDAO.delete(jobSkillID);

        Optional<JobSkill> jobSkillOptional = jobSkillDAO.findById(jobSkillID);

        Assertions.assertTrue(jobSkillOptional.isEmpty());
    }
}