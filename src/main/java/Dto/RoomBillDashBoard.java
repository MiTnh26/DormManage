package Dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RoomBillDashBoard {
    private String roomName;
    private long student;
    private long electricNumber;
    private long waterNumber;
    private String term;
    private int year;
    private String amount;
    private long s;
    private String status;
    private String date;
}
