package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BedDetailDto {
    private String roomName;
    private long totalBed;
    private String freeBed;
    private String usedBed;
    private String bookingBed;
    private long pricePerMonth;
}
