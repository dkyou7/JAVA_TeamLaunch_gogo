package com.example.domain.user;

import com.example.domain.company.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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


    public void setCompany(Company company) {
        this.company = company;
        company.getUserList().add(this);
    }

    @Builder
    public User(Long id, String nickname, String email, String password, Company company) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.company = company;
    }
}
