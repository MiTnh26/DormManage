package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
@Setter
public class RoomBill {
    private int billId;
    private long electricMoney;
    private long waterMoney;
    private String roomName;
    private String billStatus;
    private Long totalAmount;
    private String description;
    private Timestamp dayCreate;
    private Timestamp paymentDate;
    private long electricNumber;
    private long waterNumber;
    private String rollName;
    private String term;
    private int year;
}
