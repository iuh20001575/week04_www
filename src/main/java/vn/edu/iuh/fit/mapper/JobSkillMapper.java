package vn.edu.iuh.fit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.iuh.fit.entities.Job;
import vn.edu.iuh.fit.entities.JobSkill;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillLevel;

public class JobSkillMapper implements RowMapper<JobSkill> {
    @Override
    public JobSkill mapRow(ResultSet rs, int rowNum) throws SQLException {
        String moreInfos = rs.getString("more_infos");
        int skillLevelInt = rs.getInt("skill_level");
        String skillId = rs.getString("skill_id");
        String jobId = rs.getString("job_id");

        Job job = new Job(UUID.fromString(jobId));
        Skill skill = new Skill(UUID.fromString(skillId));
        SkillLevel skillLevel = SkillLevel.convert(skillLevelInt);

        return new JobSkill(moreInfos, skillLevel, job, skill);
    }
}
