package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class RoomBillAdminDto {
    private int billId;
    private String electricMoney;
    private String waterMoney;
    private String roomName;
    private String billStatus;
    private String totalAmount;
    private String description;
    private Timestamp dayCreate;
    private Timestamp paymentDate;
    private long electricNumber;
    private long waterNumber;
    private String rollName;
    private String term;
    private int year;
}
