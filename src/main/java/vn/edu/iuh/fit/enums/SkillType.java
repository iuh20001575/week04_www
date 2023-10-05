package vn.edu.iuh.fit.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum SkillType {
    HARD(0), SOFT(1), FULL(3);

    private final int value;

    SkillType(int i) {
        value = i;
    }

    public static SkillType convert(int value) {
        return Stream.of(SkillType.values())
                .filter(skill -> skill.value == value)
                .findFirst().orElseThrow(IllegalAccessError::new);
    }

}
