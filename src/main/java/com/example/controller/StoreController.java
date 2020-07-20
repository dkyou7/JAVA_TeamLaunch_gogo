package com.example.controller;

import com.example.domain.Foo;
import com.example.domain.option.Option;
import com.example.domain.option.OptionDto;
import com.example.domain.option.OptionType;
import com.example.domain.store.Store;
import com.example.domain.store.StoreDto;
import com.example.domain.store.StoreType;
import com.example.domain.storeOption.StoreOption;
import com.example.domain.storeOption.StoreOptionDto;
import com.example.service.OptionService;
import com.example.service.StoreOptionService;
import com.example.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final OptionService optionService;
    private final StoreOptionService storeOptionService;

    @GetMapping("/store/{id}")
    public String dispStoreDetail(@PathVariable("id") Long storeId, Model model){
        Store findStore = storeService.findById(storeId);
        model.addAttribute("storeForm",new StoreDto(findStore));
        return "store/details";
    }

    @GetMapping("/store/list")
    public String dispStoreList(Model model){
        List<Store> storeList = storeService.findAll();
        List<StoreDto> collect = storeList.stream().map(StoreDto::new).collect(Collectors.toList());
        model.addAttribute("stores",collect);
        return "store/storeList";
    }

    @GetMapping("/store/new")
    public String createStoreForm(Model model){
        model.addAttribute("storeForm",new StoreDto());
        model.addAttribute("storeOption",new StoreOptionDto());
        model.addAttribute("allItems", optionService.findAllName());

        Foo foo = new Foo();
        List<String> checkedItems = new ArrayList<>();
        // value1 will be checked by default.
        foo.setCheckedItems(checkedItems);
        model.addAttribute("foo", foo);

        return "store/createStoreForm";
    }

    @PostMapping("/store/new")
    public String createStore(@Valid StoreDto storeDto,
                              @RequestParam("typeId") String storeType,
                              @ModelAttribute(value="foo") Foo foo,
                              BindingResult result) {

        if (result.hasErrors()) {
            return "store/createStoreForm";
        }
        List<String> checkedItems = foo.getCheckedItems();

        for(String checked : checkedItems){
            System.out.println("checked = " + checked);
        }
        Store store = Store.builder()
                .name(storeDto.getName())
                .address(storeDto.getAddress())
                .phoneNum(storeDto.getPhoneNum())
                .storeType(StoreType.valueOf(storeType))
                .icecreamCount(0)
                .build();

        if (storeService.save(store)) {
            // 등록에 성공했을 경우
            return "redirect:/";
        } else {
            // 등록 실패했을 경우
            return "store/createStoreForm";
        }
    }
}
