package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Role {
    private Integer roleId;
    private String roleName;
}
