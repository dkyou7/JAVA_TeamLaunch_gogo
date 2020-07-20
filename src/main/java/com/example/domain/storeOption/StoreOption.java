package com.example.domain.storeOption;


import com.example.domain.option.Option;
import com.example.domain.store.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class StoreOption {

    @Id
    @GeneratedValue
    @Column(name = "store_option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;


}
