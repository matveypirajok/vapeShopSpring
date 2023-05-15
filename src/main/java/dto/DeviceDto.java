package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private Long providerId;
    private String providerName;
    private Long storeId;
    private String storeName;
}
