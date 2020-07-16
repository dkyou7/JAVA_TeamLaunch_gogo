package com.example.domain.company;

import com.example.domain.store.Store;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String name;


    public CompanyDto(Company company) {
        id = company.getId();
        name = company.getName();
    }
}
