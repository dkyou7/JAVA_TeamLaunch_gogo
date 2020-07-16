package com.example.service;

import com.example.domain.company.Company;
import com.example.domain.reserve.Reserve;
import com.example.domain.store.Store;
import com.example.domain.user.User;
import com.example.repository.CompanyRepository;
import com.example.repository.ReserveRepository;
import com.example.repository.StoreRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveService {

    private final ReserveRepository reserveRepository;
    private final CompanyRepository companyRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public List<Reserve> findAll() {
        return reserveRepository.findAll();
    }

    @Transactional
    public Long reserve(Long companyId, Long storeId, String reserver, int count){

        // 엔티티 조회
        Company company = companyRepository.findById(companyId).orElseThrow(EntityNotFoundException::new);
        Store store = storeRepository.findById(storeId).orElseThrow(EntityNotFoundException::new);

        // 가게 예약 생성
        Reserve reserveStore = Reserve.createStore(store,reserver,count);

        // 회사 예약 생성
        Company reserveCompany = Company.createCompany(company,reserveStore);

        companyRepository.save(reserveCompany);

        return reserveCompany.getId();
    }

}
