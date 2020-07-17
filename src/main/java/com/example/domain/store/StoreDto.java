package com.example.domain.store;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreDto {

    private Long id;
    private String name;
    private String address;
    private String phoneNum;
    private StoreType storeType;

    public StoreDto(Store store) {
        id = store.getId();
        name = store.getName();
        address = store.getAddress();
        phoneNum = store.getPhoneNum();
        storeType = store.getStoreType();
    }
}
