package vapeShop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static vapeShop.data.MessageData.NAME_MESSAGE;
import static vapeShop.data.MessageData.ADDRESS_MESSAGE;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDto {
    private Long id;

    @NotBlank(message = NAME_MESSAGE)
    private String name;

    @NotBlank(message = ADDRESS_MESSAGE)
    private String address;
}
