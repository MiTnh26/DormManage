package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@Builder
@ToString
public class InvoiceDetailDto {
    private String rollName;
    private String room;
    private int bed;
    private String createDate;
    private String paymentDate;
    private String paymentStatus;
    private String description;
    private Map<String, String> money;
    private String moneyTotal;
    private String status;
}
