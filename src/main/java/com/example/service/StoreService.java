package com.example.service;

import com.example.domain.store.Store;
import com.example.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StoreService {


    @Autowired
    private StoreRepository storeRepository;

    @Transactional
    public Boolean save(Store s){
        List<Store> stores = storeRepository.findByStoreName(s.getStoreName());
        if (stores.isEmpty()){
            storeRepository.save(s);
            return true;
        }else{
            return false;
        }
    }
}
