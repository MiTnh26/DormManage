package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class PaymentDto {
    private Integer paymentId;
    private String roomName;
    private String createDate;
    private String description;
    private String status;
    private String totalAmount;
    private String totalAmountPaid;
    private String totalAmountRemain;
    private String rollId;

}
