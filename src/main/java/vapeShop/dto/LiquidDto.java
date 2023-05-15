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
public class LiquidDto {
    private Long id;

    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @NotBlank(message = "Вкус не может быть пустым")
    private String taste;

    @Min(value = 0, message = "Цена не может быть отрицательной")
    private Double price;

    private Long providerId;
    private String providerName;
    private Long storeId;
    private String storeName;
}
