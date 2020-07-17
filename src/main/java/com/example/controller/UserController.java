package com.example.controller;

import com.example.domain.company.Company;
import com.example.domain.company.CompanyDto;
import com.example.domain.store.Store;
import com.example.domain.store.StoreDto;
import com.example.domain.user.User;
import com.example.domain.user.UserDto;
import com.example.service.CompanyService;
import com.example.service.StoreService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping("/user/list")
    public String dispUserList(Model model){
        List<User> userList = userService.findAll();
        List<UserDto> collect = userList.stream().map(UserDto::new).collect(Collectors.toList());

        model.addAttribute("users",collect);
        return "user/userList";
    }

    @GetMapping("/user/new")
    public String createStoreForm(Model model){
        List<Company> companyList = companyService.findAll();
        List<CompanyDto> companyDtos = companyList.stream().map(CompanyDto::new).collect(Collectors.toList());

        model.addAttribute("companies",companyDtos);
        model.addAttribute("userForm",new UserDto());
        return "user/createUserForm";
    }

    @PostMapping("/user/new")
    public String createUser(@Valid UserDto userDto,
                             @RequestParam("companyId") Long companyId,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "user/createUserForm";
        }
        Company findCompany = companyService.findById(companyId);
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .company(findCompany)
                .build();

        if (userService.save(user)) {
            // 등록에 성공했을 경우
            return "redirect:/";
        } else {
            // 등록 실패했을 경우
            return "store/createStoreForm";
        }
    }
}
