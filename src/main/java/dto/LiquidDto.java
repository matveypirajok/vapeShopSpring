package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LiquidDto {
    private Long id;
    private String name;
    private String taste;
    private Double price;
    private Long providerId;
    private String providerName;
    private Long storeId;
    private String storeName;
}
