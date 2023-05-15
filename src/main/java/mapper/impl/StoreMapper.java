package mapper.impl;

import dto.StoreDto;
import entity.Store;
import mapper.Mapper;

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

    public Store objDtoToObj(StoreDto storeDto, Store store) {
        store.setId(storeDto.getId());
        store.setName(storeDto.getName());
        store.setAddress(storeDto.getAddress());
        return store;
    }
}
