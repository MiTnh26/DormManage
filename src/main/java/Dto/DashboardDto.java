package Dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class DashboardDto {
    private long totalBed;
    private long freeBed;
    private long usedBed;
    private long student;
    private List<RoomBillDashBoard> roomBillDashBoards;
}
