package com.example.repository;

import com.example.domain.option.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option,Long> {

    @Query("select o.name from Option o")
    List<String> getOptionName();
}
