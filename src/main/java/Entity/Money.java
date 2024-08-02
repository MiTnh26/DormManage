package Entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Money {
    private Integer moneyId;
    private String moneyType;
    private Long amount;
    private Integer bedTotal;
    private String roomType;
}
