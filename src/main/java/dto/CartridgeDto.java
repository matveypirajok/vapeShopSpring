package dto;

import lombok.*;


@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartridgeDto {
    private Long id;
    private String name;
    private Double resistance;
    private Double price;
    private Long providerId;
    private String providerName;
    private Long storeId;
    private String storeName;
}
