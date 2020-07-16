package com.example.domain;

import com.example.domain.company.Company;
import com.example.domain.store.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "company_store")
@Getter
public class Place {

    @Id
    @GeneratedValue
    @Column(name = "company_store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private int count;          // 방문 횟수
    private int reserved_count; // 한번에 몇명 방문했는지.
}
