package vapeShop.mapper.impl;

import vapeShop.dto.DeviceDto;
import vapeShop.entity.Device;
import vapeShop.entity.Provider;
import vapeShop.entity.Store;
import lombok.RequiredArgsConstructor;
import vapeShop.mapper.Mapper;
import org.springframework.stereotype.Component;
import vapeShop.repositories.ProviderRep;
import vapeShop.repositories.StoreRep;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeviceMapper implements Mapper<Device, DeviceDto> {
    private final StoreRep storeRep;
    private final ProviderRep providerRep;

    @Override
    public Device mapToEntity(DeviceDto object) {
        return Device.builder()
                .id(object.getId())
                .price(object.getPrice())
                .name(object.getName())
                .brand(object.getBrand())
                .provider(getProvider(object.getProviderId()))
                .store(getStore(object.getStoreId()))
                .build();
    }

    @Override
    public DeviceDto mapToDTO(Device object) {
        return DeviceDto.builder()
                .id(object.getId())
                .brand(object.getBrand())
                .name(object.getName())
                .price(object.getPrice())
                .providerId(object.getProvider().getId())
                .providerName(object.getProvider().getName())
                .storeId(object.getStore().getId())
                .storeName(object.getStore().getName())
                .build();
    }

    private Store getStore(Long storeId) {
        return Optional.ofNullable(storeId)
                .flatMap(storeRep::findById)
                .orElse(null);
    }

    private Provider getProvider(Long providerId) {
        return Optional.ofNullable(providerId)
                .flatMap(providerRep::findById)
                .orElse(null);
    }
}
