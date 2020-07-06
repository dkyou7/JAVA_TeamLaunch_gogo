package com.example.service;

import com.example.domain.Store;
import com.example.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class StoreService {


    @Autowired
    private StoreRepository storeRepository;

    @Transactional
    private Store createStore(Long id){
        Store store = storeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Store savedStore = storeRepository.save(store);
        return savedStore;
    }
}
