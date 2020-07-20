package com.example.domain.store;

import com.example.domain.option.Option;
import com.example.domain.storeOption.StoreOption;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StoreDto {

    private Long id;
    private String name;
    private String address;
    private String phoneNum;
    private StoreType storeType;
    private int icecreamCount;

    public StoreDto(Store store) {
        id = store.getId();
        name = store.getName();
        address = store.getAddress();
        phoneNum = store.getPhoneNum();
        storeType = store.getStoreType();
        icecreamCount = store.getIcecreamCount();
    }
}
