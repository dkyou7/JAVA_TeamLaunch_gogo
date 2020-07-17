package com.example.service;

import com.example.domain.company.Company;
import com.example.domain.user.User;
import com.example.repository.CompanyRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    public boolean save(Company company) throws Throwable {
        Company findCompany = companyRepository.findByName(company.getName()).orElseThrow(EntityNotFoundException::new);
        if (findCompany == null){
            companyRepository.save(company);
            return true;
        }else{
            return false;
        }
    }

    public Company findById(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(EntityNotFoundException::new);
    }
}
