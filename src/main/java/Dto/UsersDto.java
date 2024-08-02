package Dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UsersDto {
    private Integer userId;
    private String fullName;
    private String gmail;
    private String gender;
    private String role;
    private String status;
}
