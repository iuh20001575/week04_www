package vn.edu.iuh.fit.mapper;

import org.springframework.jdbc.core.RowMapper;
import vn.edu.iuh.fit.entities.Address;
import vn.edu.iuh.fit.entities.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CompanyMapper implements RowMapper<Company> {
    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID id = UUID.fromString(rs.getString("comp_id"));
        String about = rs.getString("about");
        String email = rs.getString("email");
        String name = rs.getString("comp_name");
        String phone = rs.getString("phone");
        String webUrl = rs.getString("web_url");
        UUID addressId = UUID.fromString(rs.getString("address"));

        return new Company(id, about, email, name, phone, webUrl, new Address(addressId));
    }
}
