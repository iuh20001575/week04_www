package vn.edu.iuh.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import vn.edu.iuh.fit.dao.AddressDAO;
import vn.edu.iuh.fit.entities.Address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ContextConfiguration(classes = WwwWeek03Application.class)
class AddressTests {
    @Autowired
    private AddressDAO addressDAO;

//    @Test
    void create() {
        int length = 5;
        int count = 0;

        Address address;
        for (int i = 1; i <= length; ++i) {
            address = new Address(UUID.randomUUID(), "Street #" + i, "City #" + i, (short) i, "#"  + i, "Code" + i);
            if (addressDAO.create(address))
                count++;
        }

        Assertions.assertEquals(count, length);
    }

    @Test
    void getAll() {
        List<Address> all = addressDAO.getAll();

        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    void findById() {
        Optional<Address> addressOptional = addressDAO.findById(UUID.fromString("f1fd5b76-0491-476e-ac85-1a613d25b24e"));

        Assertions.assertTrue(addressOptional.isPresent());
    }

    @Test
    void update() {
        Optional<Address> addressOptional = addressDAO.findById(UUID.fromString("f1fd5b76-0491-476e-ac85-1a613d25b24e"));
        String newZipCode = "1234567";
        
        if (addressOptional.isEmpty())
            Assertions.fail();

        Address address = addressOptional.get();

        address.setZipcode(newZipCode);

        if (!addressDAO.update(address))
            Assertions.fail();

        Optional<Address> find = addressDAO.findById(address.getId());

        Assertions.assertTrue(find.isPresent() && find.get().getZipcode().equals(newZipCode));
    }

//    @Test
    void delete() {
        UUID uuid = UUID.fromString("2536442d-8a32-4acf-9dfc-7a4e25edb329");

        addressDAO.delete(uuid);

        Optional<Address> addressOptional = addressDAO.findById(uuid);

        Assertions.assertFalse(addressOptional.isPresent());
    }
}