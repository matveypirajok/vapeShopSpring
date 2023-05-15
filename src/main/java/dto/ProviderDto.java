package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDto {
    private Long id;
    private String name;
    private String address;
}
