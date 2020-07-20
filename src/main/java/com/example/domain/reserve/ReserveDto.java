package com.example.domain.reserve;

import com.example.domain.store.Store;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReserveDto {

    private Long id;
    private String name;
    private String address;
    private String phoneNum;
    private String reserver;
    private int count;
    private Long storeId;

    public ReserveDto(Reserve reserve) {
        id = reserve.getId();
        reserver = reserve.getReserver();
        name = reserve.getStore().getName();
        address = reserve.getStore().getAddress();
        phoneNum = reserve.getStore().getPhoneNum();
        count= reserve.getCount();
        storeId = reserve.getStore().getId();
    }

}
