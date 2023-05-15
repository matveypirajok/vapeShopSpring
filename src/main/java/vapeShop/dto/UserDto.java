package vapeShop.dto;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String phone;
    private String email;
    private String role;
}
