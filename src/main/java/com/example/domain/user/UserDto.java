package com.example.domain.user;

import com.example.domain.company.Company;
import com.example.domain.option.OptionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.Option;
import java.util.List;

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
