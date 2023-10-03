package vn.edu.iuh.fit.enums;

import lombok.Getter;

@Getter
public enum SkillLevel {
    BEGINNER(0), INTERMEDIATE(1), PROFICIENT(2);

    private final int value;

    SkillLevel(int value) {
        this.value = value;
    }

}
