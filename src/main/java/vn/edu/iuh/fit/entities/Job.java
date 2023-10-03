package vn.edu.iuh.fit.entities;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Job {
    private UUID id;
    private String desc;
    private String name;
    private Company company;
}
