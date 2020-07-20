package com.example.domain.option;

import com.example.domain.store.Store;
import com.example.domain.storeOption.StoreOption;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Option {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "option",cascade = CascadeType.ALL)
    private List<StoreOption> storeOptions;

    @Builder
    public Option(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
