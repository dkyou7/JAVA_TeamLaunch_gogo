package com.example.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String nickname;
    private String email;
    private String password;

    public UserDto(User user) {
        id = user.getId();
        nickname = user.getNickname();
        email = user.getEmail();
    }
}
