package vn.edu.iuh.fit.enums;

import lombok.Getter;

@Getter
public enum SkillType {
    HARD(0), SOFT(1), FULL(3);

    private final int value;

    SkillType(int i) {
        value = i;
    }

}
