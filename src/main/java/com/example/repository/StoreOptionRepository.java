package com.example.repository;

import com.example.domain.storeOption.StoreOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreOptionRepository extends JpaRepository<StoreOption,Long> {

    @Query("select so from StoreOption so where so.store.id = ?1")
    List<StoreOption> findByStoreId(Long id);
}
