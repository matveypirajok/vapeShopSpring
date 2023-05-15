package vapeShop.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vapeShop.dto.StoreDto;
import vapeShop.entity.Store;
import vapeShop.mapper.Mapper;

@Component
@RequiredArgsConstructor
public class StoreMapper implements Mapper<Store, StoreDto> {

    @Override
    public Store mapToEntity(StoreDto object) {
        return Store.builder()
                .id(object.getId())
                .name(object.getName())
                .address(object.getAddress())
                .build();
    }

    @Override
    public StoreDto mapToDTO(Store object) {
        return StoreDto.builder()
                .id(object.getId())
                .address(object.getAddress())
                .name(object.getName())
                .build();
    }
}
