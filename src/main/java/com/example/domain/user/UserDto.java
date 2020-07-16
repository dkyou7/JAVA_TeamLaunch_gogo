package com.example.domain.user;

import com.example.domain.company.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String nickname;
    private String email;
    private String password;
    private Company company;

    public UserDto(User user) {
        id = user.getId();
        nickname = user.getNickname();
        email = user.getEmail();
        company = user.getCompany();
    }
}
