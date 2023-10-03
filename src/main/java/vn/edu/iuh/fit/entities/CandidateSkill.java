package vn.edu.iuh.fit.entities;

import lombok.*;
import vn.edu.iuh.fit.enums.SkillLevel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CandidateSkill {
    private String moreInfos;
    private SkillLevel skillLevel;
    private Skill skill;
    private Candidate candidate;
}
