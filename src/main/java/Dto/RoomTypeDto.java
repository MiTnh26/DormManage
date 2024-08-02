package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RoomTypeDto {
    private String key;
    private int bed;
    private String amount;
}
