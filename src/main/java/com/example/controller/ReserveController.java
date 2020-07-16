package com.example.controller;

import com.example.domain.company.Company;
import com.example.domain.company.CompanyDto;
import com.example.domain.store.Store;
import com.example.domain.store.StoreDto;
import com.example.service.CompanyService;
import com.example.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ReserveController {

    private final CompanyService companyService;
    private final StoreService storeService;

    @GetMapping(value = "/reserve")
    public String createReserve(Model model){
        List<Company> companies = companyService.findAll();
        List<Store> stores = storeService.findAll();

        List<CompanyDto> companyDtos = companies.stream().map(CompanyDto::new).collect(Collectors.toList());
        List<StoreDto> storeDtos = stores.stream().map(StoreDto::new).collect(Collectors.toList());

        model.addAttribute("companies",companyDtos);
        model.addAttribute("stores",storeDtos);

        return "reserve/reserveForm";
    }
}
