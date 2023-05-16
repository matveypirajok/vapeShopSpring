package vapeShop.entity;

import jakarta.persistence.*;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static vapeShop.data.EntityData.PROVIDER;
import static vapeShop.data.EntityData.PROVIDER_ID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = PROVIDER)

public class Provider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PROVIDER_ID)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Builder.Default
    @OneToMany(mappedBy = PROVIDER, cascade = CascadeType.ALL)
    private List<Liquid> liquids = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = PROVIDER, cascade = CascadeType.ALL)
    private List<Accessory> accessories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = PROVIDER, cascade = CascadeType.ALL)
    private List<Cartridge> cartridges = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = PROVIDER, cascade = CascadeType.ALL)
    private List<Device> devices = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = PROVIDER, cascade = CascadeType.ALL)
    private List<Evaporator> evaporators = new ArrayList<>();
}
