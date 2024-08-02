package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MoneyDto {
    private Integer moneyId;
    private String moneyType;
    private String amount;
    private Integer bedTotal;
    private String roomType;
}
