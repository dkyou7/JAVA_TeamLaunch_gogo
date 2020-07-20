package com.example.domain.storeOption;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreOptionDto {

    private Long id;
    private Long storeId;
    private Long optionId;

    public StoreOptionDto(StoreOption storeOption) {
        id = storeOption.getId();
        storeId = storeOption.getStore().getId();
        optionId = storeOption.getOption().getId();
    }

}
