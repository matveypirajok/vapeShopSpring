package vapeShop.mapper.impl;

import vapeShop.dto.ProviderDto;
import vapeShop.entity.Provider;
import lombok.RequiredArgsConstructor;
import vapeShop.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProviderMapper implements Mapper<Provider, ProviderDto> {

    @Override
    public Provider mapToEntity(ProviderDto object) {
        return Provider.builder()
                .id(object.getId())
                .name(object.getName())
                .address(object.getAddress())
                .build();
    }

    @Override
    public ProviderDto mapToDTO(Provider object) {
        return ProviderDto.builder()
                .id(object.getId())
                .address(object.getAddress())
                .name(object.getName())
                .build();
    }
}
