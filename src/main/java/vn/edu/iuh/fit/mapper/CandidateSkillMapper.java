package vn.edu.iuh.fit.mapper;

import org.springframework.jdbc.core.RowMapper;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.entities.CandidateSkill;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillLevel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CandidateSkillMapper implements RowMapper<CandidateSkill> {
    @Override
    public CandidateSkill mapRow(ResultSet rs, int rowNum) throws SQLException {
        String moreInfos = rs.getString("more_infos");
        int skillLevelInt = rs.getInt("skill_level");
        String skillId = rs.getString("skill_id");
        String canId = rs.getString("can_id");

        Candidate candidate = new Candidate(UUID.fromString(canId));
        Skill skill = new Skill(UUID.fromString(skillId));
        SkillLevel skillLevel = SkillLevel.convert(skillLevelInt);

        return new CandidateSkill(moreInfos, skillLevel, skill, candidate);
    }
}
