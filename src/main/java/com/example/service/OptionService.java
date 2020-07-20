package com.example.service;

import com.example.domain.option.Option;
import com.example.domain.storeOption.StoreOption;
import com.example.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionRepository;

    @Transactional
    public List<String> findAllName(){
        return optionRepository.getOptionName();
    }
}
