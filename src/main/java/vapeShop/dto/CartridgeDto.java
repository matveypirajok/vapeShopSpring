package vapeShop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartridgeDto {
    private Long id;

    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @Min(value = 0, message = "Сопротивление не может быть отрицательной")
    private Double resistance;

    @Min(value = 0, message = "Цена не может быть отрицательной")
    private Double price;

    private Long providerId;
    private String providerName;
    private Long storeId;
    private String storeName;
}
