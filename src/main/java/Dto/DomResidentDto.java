package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class DomResidentDto {
    private String studentId;
    private String bedInformation;
    private String checkInDate;
    private String checkOutDate;
    private Long price;
    private String semester;
    private Integer year;
}
