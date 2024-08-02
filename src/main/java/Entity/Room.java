package Entity;

import Enum.RoomStatus;
import Enum.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Room {
    private String roomName;
    private String roomType;
    private Integer domId;
    private RoomStatus roomStatus;
    private Integer floor;
}
