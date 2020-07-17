package com.example.domain.store;

import lombok.*;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    private StoreType storeType;

    @Builder
    public Store(Long id, String name, String address, String phoneNum, StoreType storeType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.storeType = storeType;
    }
}
