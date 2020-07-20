package com.example.domain.option;

import com.example.domain.store.Store;
import com.example.domain.store.StoreType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OptionDto {

    private Long id;
    private String name;

    public OptionDto(Option option) {
        id = option.getId();
        name = option.getName();
    }
}
