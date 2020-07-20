package com.example.repository;

import com.example.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findByName(String storeName);

    @Query("select o.name from Option o")
    List<String> findAllOptionName();
}
