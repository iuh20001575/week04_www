package vn.edu.iuh.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.dao.AddressDAO;
import vn.edu.iuh.fit.dao.CompanyDAO;
import vn.edu.iuh.fit.entities.Address;
import vn.edu.iuh.fit.entities.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class CompanyTests {
    private final AddressDAO addressDAO;
    private final CompanyDAO companyDAO;

    @Autowired
    public CompanyTests(AddressDAO addressDAO, CompanyDAO companyDAO) {
        this.addressDAO = addressDAO;
        this.companyDAO = companyDAO;
    }


//    @Test
    void create() {
        UUID uuid = UUID.randomUUID();
        Address address = new Address(uuid, "Street", "City", (short) 1, "Number", "Zipcode");
        boolean b = addressDAO.create(address);

        if (!b)
            Assertions.fail();

        UUID uuidCompany = UUID.randomUUID();
        Company company = new Company(uuidCompany, "About", "email@gmail.com", "Name", "0123456789", "url", address);
        boolean b1 = companyDAO.create(company);

        if (!b1)
            Assertions.fail();

        Optional<Company> companyOptional = companyDAO.findById(uuidCompany);

        Assertions.assertTrue(companyOptional.isPresent());
    }

    @Test
    void getAll() {
        List<Company> companyList = companyDAO.getAll();

        Assertions.assertFalse(companyList.isEmpty());
    }

    @Test
    void findById() {
        Optional<Company> companyOptional = companyDAO.findById(UUID.fromString("7ab4d0b4-61bd-4b04-8aee-95a6f3da48ea"));

        Assertions.assertTrue(companyOptional.isPresent() && companyOptional.get().getAddress().getId().equals(UUID.fromString("92c73354-c9b2-40ab-a6cb-b65d123a53a9")));
    }

    @Test
    void update() {
        UUID uuid = UUID.fromString("7ab4d0b4-61bd-4b04-8aee-95a6f3da48ea");
        Optional<Company> companyOptional = companyDAO.findById(uuid);

        if (companyOptional.isEmpty())
            Assertions.fail();

        Company company = companyOptional.get();
        String newAbout = "New about";

        company.setAbout(newAbout);

        boolean update = companyDAO.update(company);

        if (!update)
            Assertions.fail();

        Optional<Company> companyUpdatedOptional = companyDAO.findById(uuid);

        if (companyUpdatedOptional.isEmpty())
            Assertions.fail();

        Assertions.assertEquals(companyOptional.get().getAbout(), newAbout);
    }

//    @Test
    void delete() {
        UUID uuid = UUID.fromString("8d714075-7880-43cc-b318-872000bf9dc8");
        boolean delete = companyDAO.delete(uuid);

        if (!delete)
            Assertions.fail();

        Optional<Company> companyOptional = companyDAO.findById(uuid);

        Assertions.assertTrue(companyOptional.isEmpty());
    }
}
