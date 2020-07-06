package com.example.controller;

import com.example.domain.StoreForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {


    @GetMapping("/store/new")
    public String createStoreForm(Model model){
        model.addAttribute("storeForm",new StoreForm());
        return "board/createStoreForm";
    }
}
