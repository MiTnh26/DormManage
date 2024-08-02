package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Builder
@Getter
@ToString
public class Dom {
    private Integer domId;
    private String domName;
    private Integer numberOfFloor;
    private String domGender;
}
