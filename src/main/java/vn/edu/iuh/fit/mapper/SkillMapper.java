package vn.edu.iuh.fit.mapper;

import org.springframework.jdbc.core.RowMapper;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SkillMapper implements RowMapper<Skill> {
    @Override
    public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("skill_id");
        String description = rs.getString("skill_description");
        String name = rs.getString("skill_name");
        int type = rs.getInt("type");

        return new Skill(UUID.fromString(id), description, name, SkillType.convert(type));
    }
}
