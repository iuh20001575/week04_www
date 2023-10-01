package vn.edu.iuh.fit.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private UUID id;
    private String street;
    private String city;
    private short country;
    private String number;
    private String zipcode;

    public Address(UUID id) {
        this.id = id;
    }
}
