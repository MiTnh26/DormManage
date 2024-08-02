package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BedTotalDto {
    private int floor;
    private long free;
    private long used;
    private long sum;
}
