package vn.edu.iuh.fit.entities;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    private UUID id;
    private LocalDate dob;
    private String email;
    private String fullName;
    private String phone;
    private Address address;
}
