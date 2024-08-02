package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GoogleUser {
    private String email;
    private String picture;
    private String name;
    private String rollName;

}
