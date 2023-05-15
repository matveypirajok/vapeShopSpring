package vapeShop.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "provider")

public class Provider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Builder.Default
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Liquid> liquids = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Accessory> accessories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Cartridge> cartridges = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Device> devices = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Evaporator> evaporators = new ArrayList<>();
}
