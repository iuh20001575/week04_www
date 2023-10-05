package vn.edu.iuh.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.dao.SkillDAO;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class SkillTests {
    private final SkillDAO skillDAO;

    @Autowired
    SkillTests(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }


    @Test
    void create() {
        int count = 0;

        for (int i = 0; i < 10; i++) {
            UUID uuid = UUID.randomUUID();
            Skill skill = new Skill(uuid, "Description", "Name", SkillType.SOFT);

            boolean b = skillDAO.create(skill);

            if (!b)
                Assertions.fail();

            Optional<Skill> jobOptional = skillDAO.findById(uuid);

            if (jobOptional.isPresent())
                count++;
        }

        Assertions.assertEquals(count, 10);
    }

    @Test
    void getAll() {
        List<Skill> skills = skillDAO.getAll();

        Assertions.assertFalse(skills.isEmpty());
    }

    @Test
    void findById() {
        Optional<Skill> skillOptional = skillDAO.findById(UUID.fromString("8f95d7bb-d6a7-4f12-a8e8-cfb2fd43bebb"));

        Assertions.assertTrue(skillOptional.isPresent());
    }

    @Test
    void update() {
        UUID uuid = UUID.fromString("8f95d7bb-d6a7-4f12-a8e8-cfb2fd43bebb");

        Optional<Skill> skillOptional = skillDAO.findById(uuid);

        if (skillOptional.isEmpty())
            Assertions.fail();

        Skill skill = skillOptional.get();
        String newDesc = "New desc";

        skill.setDescription(newDesc);

        boolean updated = skillDAO.update(skill);

        if (!updated)
            Assertions.fail();


        Optional<Skill> skillUpdatedOptional = skillDAO.findById(uuid);

        Assertions.assertTrue(skillUpdatedOptional.isPresent() && skillUpdatedOptional.get().getDescription().equals(newDesc));
    }

    @Test
    void delete() {
        UUID uuid = UUID.fromString("2c5102c0-9430-4be4-9e3c-6dd2fd08016d");

        boolean deleted = skillDAO.delete(uuid);

        if (!deleted)
            Assertions.fail();

        Optional<Skill> skillOptional = skillDAO.findById(uuid);

        Assertions.assertTrue(skillOptional.isEmpty());
    }
}