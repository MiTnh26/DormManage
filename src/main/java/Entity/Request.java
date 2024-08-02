package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@Setter
@ToString
public class Request {
    private Integer requestId;
    private String requestStatus;
    private String requestDetail;
    private String rollId;
    private String requestType;
    private String domName;
    private Integer floor;
    private String roomName;
    private String term;
    private Timestamp createDate;
    private String roomType;
    private Integer bed;
    private Timestamp checkOutDate;
}
