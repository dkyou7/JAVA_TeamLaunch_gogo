package com.example.controller;

import com.example.domain.company.Company;
import com.example.domain.company.CompanyDto;
import com.example.domain.reserve.Reserve;
import com.example.domain.reserve.ReserveDto;
import com.example.domain.store.Store;
import com.example.domain.store.StoreDto;
import com.example.service.CompanyService;
import com.example.service.ReserveService;
import com.example.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ReserveController {

    private final CompanyService companyService;
    private final StoreService storeService;
    private final ReserveService reserveService;

    @GetMapping(value = "/reserve")
    public String createReserve(Model model){
        List<Company> companies = companyService.findAll();
        List<Store> stores = storeService.findAll();
        List<StoreDto> storeDtos = stores.stream().map(StoreDto::new).collect(Collectors.toList());
        List<CompanyDto> companyDtos = companies.stream().map(CompanyDto::new).collect(Collectors.toList());


        model.addAttribute("companies",companyDtos);
        model.addAttribute("stores",storeDtos);

        return "reserve/reserveForm";
    }
    @PostMapping(value = "/reserve")
    public String create(@RequestParam("companyId") Long companyId,
                         @RequestParam("storeId") Long storeId,
                         @RequestParam("reserver") String reserver,
                         @RequestParam("count") int count){
        reserveService.reserve(companyId,storeId,reserver,count);
        return "redirect:/";
    }

    @GetMapping(value = "/reserve/list")
    public String orderList(Model model){
        List<Reserve> reserves = reserveService.findAll();
        List<ReserveDto> reserveDtos = reserves.stream().map(ReserveDto::new).collect(Collectors.toList());
        model.addAttribute("reserves",reserveDtos);

        return "reserve/reserveList";
    }
}
