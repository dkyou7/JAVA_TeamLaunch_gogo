package com.example.domain.store;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phoneNum;

    @Builder
    public Store(String storeName, String address, String phoneNum) {
        this.name = storeName;
        this.address = address;
        this.phoneNum = phoneNum;
    }
}
