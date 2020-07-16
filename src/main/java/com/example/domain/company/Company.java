package com.example.domain.company;


import com.example.domain.reserve.Reserve;
import com.example.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
public class Company {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "company")
    private List<User> userList = new ArrayList<>();

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Reserve> reserves = new ArrayList<>();

    @Builder
    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Company createCompany(Company com ,Reserve reserve) {
        Company company = com;
        company.addReserve(reserve);
        return company;
    }

    private void addReserve(Reserve reserve) {
        reserves.add(reserve);
        reserve.setCompany(this);
    }
}
