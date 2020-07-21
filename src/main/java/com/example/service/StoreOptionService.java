package com.example.service;

import com.example.domain.storeOption.StoreOption;
import com.example.repository.StoreOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreOptionService {

    private final StoreOptionRepository repository;
    @Transactional
    public List<StoreOption> findStoreOptionNames(Long id) {
        return repository.findByStoreId(id);
    }
}
