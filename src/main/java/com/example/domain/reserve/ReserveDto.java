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

    public ReserveDto(Store store) {
        id = store.getId();
        name = store.getName();
        address = store.getAddress();
        phoneNum = store.getPhoneNum();
    }
}
