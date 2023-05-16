package vapeShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import static vapeShop.data.EntityData.STORE_ID;
import static vapeShop.data.EntityData.PROVIDER_ID;
import static vapeShop.data.EntityData.DEVICE;
import static vapeShop.data.EntityData.DEVICE_ID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = DEVICE)

public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DEVICE_ID)
    private Long id;

    @Column
    private String name;

    @Column
    private String brand;

    @Column
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = STORE_ID)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PROVIDER_ID)
    private Provider provider;
}
