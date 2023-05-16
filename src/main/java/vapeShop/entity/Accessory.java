package vapeShop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

import static vapeShop.data.EntityData.STORE_ID;
import static vapeShop.data.EntityData.PROVIDER_ID;
import static vapeShop.data.EntityData.ACCESSORY;
import static vapeShop.data.EntityData.ACCESSORY_ID;
import static vapeShop.data.MessageData.*;
import static vapeShop.data.MessageData.PRICE_EMPTY_MESSAGE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = ACCESSORY)
public class Accessory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ACCESSORY_ID)
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = STORE_ID)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PROVIDER_ID)
    private Provider provider;
}
