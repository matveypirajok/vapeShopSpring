package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private Long id;
    private String name;
    private String address;
}
