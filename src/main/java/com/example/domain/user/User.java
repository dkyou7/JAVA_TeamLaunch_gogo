package com.example.domain.user;

import com.example.domain.company.Company;
import com.example.domain.enums.SocialType;
import com.example.domain.option.OptionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;
    private String email;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String principal;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    public void setCompany(Company company) {
        this.company = company;
        company.getUserList().add(this);
    }

    @Builder
    public User(String nickname, String email, String password, String principal, SocialType socialType) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.principal = principal;
        this.socialType = socialType;
    }
}
