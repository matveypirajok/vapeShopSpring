package vapeShop.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static vapeShop.data.EntityData.STORE_ID;
import static vapeShop.data.EntityData.STORE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = STORE)

public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = STORE_ID)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Builder.Default
    @OneToMany(mappedBy = STORE, cascade = CascadeType.ALL)
    private List<Accessory> accessories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = STORE, cascade = CascadeType.ALL)
    private List<Cartridge> cartridges = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = STORE, cascade = CascadeType.ALL)
    private List<Device> devices = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = STORE, cascade = CascadeType.ALL)
    private List<Evaporator> evaporators = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = STORE, cascade = CascadeType.ALL)
    private List<Liquid> liquids = new ArrayList<>();
}
