package Dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@Builder
@Getter
public class StudentBedDto {
    private String rollId;
    private String fullName;
    private String gender;
    private String gmail;
    private int bed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentBedDto person = (StudentBedDto) o;
        return rollId.equals(person.rollId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollId);
    }

}
