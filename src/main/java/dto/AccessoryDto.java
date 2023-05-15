package dto;

import lombok.*;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccessoryDto {
    private Long id;
    private String name;
    private String type;
    private Double price;
    private Long providerId;
    private String providerName;
    private Long storeId;
    private String storeName;
}
