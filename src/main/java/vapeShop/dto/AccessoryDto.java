package vapeShop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccessoryDto {
    private Long id;

    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @NotBlank(message = "Тип не может быть пустым")
    private String type;

    @Min(value = 0, message = "Цена не может быть отрицательной")
    private Double price;

    private Long providerId;
    private String providerName;
    private Long storeId;
    private String storeName;
}
