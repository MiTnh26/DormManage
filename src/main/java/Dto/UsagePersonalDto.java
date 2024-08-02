package Dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class UsagePersonalDto {
    private String termId;
    private int numOfUser;
}
