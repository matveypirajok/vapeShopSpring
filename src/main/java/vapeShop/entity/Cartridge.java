package vapeShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import static vapeShop.data.EntityData.STORE_ID;
import static vapeShop.data.EntityData.PROVIDER_ID;
import static vapeShop.data.EntityData.CARTRIDGE;
import static vapeShop.data.EntityData.CARTRIDGE_ID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = CARTRIDGE)

public class Cartridge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CARTRIDGE_ID)
    private Long id;

    @Column
    private String name;

    @Column
    private Double resistance;

    @Column
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = STORE_ID)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PROVIDER_ID)
    private Provider provider;
}
