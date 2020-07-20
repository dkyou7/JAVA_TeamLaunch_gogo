package com.example.service;

import com.example.domain.option.Option;
import com.example.domain.store.Store;
import com.example.domain.storeOption.StoreOption;
import com.example.repository.OptionRepository;
import com.example.repository.StoreOptionRepository;
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

    @Autowired
    private OptionRepository optionRepository;

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
    public Boolean save(Store s,List<String> options){
        List<Store> stores = storeRepository.findByName(s.getName());

        if (stores.isEmpty()){
            Store savedStore = storeRepository.save(s);
            for(String option : options) {
                Option findOption = optionRepository.findByName(option);
                // 옵션 가게옵션 생성
                StoreOption storeOption = StoreOption.createStoreOption(savedStore, findOption);
                // 가게 가게옵션 생성
                Store storeResult = Store.createStore(savedStore,storeOption);
                storeRepository.save(storeResult);
            }
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

    @Transactional
    public List<String> findAllOptionName() {
        return storeRepository.findAllOptionName();
    }
}
