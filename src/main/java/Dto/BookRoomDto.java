package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@Builder
@ToString
public class BookRoomDto {
    private String key;
    private String roomType;
    private String roomAmount;
    private Set<String> doms;
    private Set<Integer> floors;
    private String yourAccountBalance;
    private String minimumBalance;
    private String yourBalanceAfterBooking;
    private long numberSlotsRemaining;

}
