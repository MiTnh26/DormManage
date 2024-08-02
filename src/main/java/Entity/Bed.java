package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import Enum.BedStatus;
@Builder
@Getter
@ToString
@Setter
public class Bed {
    private Integer bedId;
    private BedStatus bedStatus;
    private String  roomName;
    private Integer floor;

    public String getKey(){
        // Neu roomName = A101 => return A
        return roomName.substring(0, 1);
    }
}
