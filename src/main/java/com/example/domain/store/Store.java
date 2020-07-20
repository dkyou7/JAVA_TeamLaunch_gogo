package com.example.domain.store;

import com.example.domain.option.Option;
import com.example.domain.reserve.Reserve;
import com.example.domain.storeOption.StoreOption;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String name;
    private String address;
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private StoreType storeType;

    private int icecreamCount;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<StoreOption> storeOptions  = new ArrayList<>();

    @Builder
    public Store(Long id, String name, String address, String phoneNum, StoreType storeType,int icecreamCount) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.storeType = storeType;
        this.icecreamCount = icecreamCount;
    }

    public static Store createStore(Store store, StoreOption storeOption) {
        store.addStoreOption(storeOption);
        return store;
    }

    private void addStoreOption(StoreOption storeOption) {
        storeOptions.add(storeOption);
        storeOption.setStore(this);
    }
}
