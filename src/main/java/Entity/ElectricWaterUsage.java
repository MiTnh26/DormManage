package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ElectricWaterUsage {
    private Integer id;
    private Long electricNumber;
    private Long waterNumber;
    private String domName;
    private Integer floor;
    private String term;
    private String roomName;
    private Integer month;
    private Integer year;
}
