package com.example.service;

import com.example.domain.store.Store;
import com.example.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Transactional
    public Boolean save(Store s){
        List<Store> stores = storeRepository.findByName(s.getName());
        if (stores.isEmpty()){
            storeRepository.save(s);
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public Store findById(Long storeId) {
        Optional<Store> findStore = storeRepository.findById(storeId);
        return findStore.orElseThrow(EntityNotFoundException::new);
    }
}
