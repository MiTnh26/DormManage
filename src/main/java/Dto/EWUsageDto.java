package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class EWUsageDto {
    private Integer id;
    private Long electricNumber;
    private Long waterNumber;
    private String domName;
    private Integer floor;
    private String term;
    private String roomName;
    private Integer month;
    private Integer year;
    private String electricMoney;
    private String waterMoney;
    private String totalMoney;
}
