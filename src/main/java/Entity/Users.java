package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Users {
    private Integer userId;
    private Integer roleId;
    private String password;
    private String gmail;
    private String gender;
    private String fullName;
    private String status;
}
