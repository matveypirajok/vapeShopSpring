package vapeShop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static vapeShop.data.MessageData.NAME_MESSAGE;
import static vapeShop.data.MessageData.PRICE_MESSAGE;
import static vapeShop.data.MessageData.TASTE_MESSAGE;
import static vapeShop.data.MessageData.PRICE_EMPTY_MESSAGE;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LiquidDto {
    private Long id;

    @NotBlank(message = NAME_MESSAGE)
    private String name;

    @NotBlank(message = TASTE_MESSAGE)
    private String taste;

    @Min(value = 0, message = PRICE_MESSAGE)
    @NotNull(message = PRICE_EMPTY_MESSAGE)
    private Double price;

    private Long providerId;
    private String providerName;
    private Long storeId;
    private String storeName;
}
