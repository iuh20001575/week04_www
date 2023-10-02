package vn.edu.iuh.fit.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.entities.Address;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AddressDAO extends AbstractDAO<Address, UUID> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    public AddressDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public boolean create(Address address) {
        String sql = "INSERT address VALUES (?,?,?,?,?,?)";

        int inserted = jdbcTemplate.update(sql, address.getId(), address.getStreet(), address.getCity(), address.getCountry(), address.getNumber(), address.getZipcode());

        return inserted > 0;
    }

    @Override
    public List<Address> getAll() {
        String sql = "SELECT * FROM address";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Address.class));
    }

    @Override
    public Optional<Address> findById(UUID uuid) {
        String sql = "SELECT * FROM address WHERE id = ?";

        try {
            Address address = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Address.class), uuid);

            return Optional.ofNullable(address);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public boolean update(Address address) {
        String sql = "UPDATE address set street = ?, city = ?, country = ?, number = ?, zipcode = ? where id = ?";

        int updated = jdbcTemplate.update(sql, address.getStreet(), address.getCity(), address.getCountry(), address.getNumber(), address.getZipcode(), address.getId());

        return updated > 0;
    }

    @Override
    public boolean delete(UUID uuid) {
        String sql = "DELETE FROM address where id = ?";

        return jdbcTemplate.update(sql, uuid) > 0;
    }
}
