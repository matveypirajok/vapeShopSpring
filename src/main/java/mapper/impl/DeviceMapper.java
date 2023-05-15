package mapper.impl;

import dto.DeviceDto;
import entity.Device;
import entity.Provider;
import entity.Store;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
import org.springframework.stereotype.Component;
import repositories.ProviderRep;
import repositories.StoreRep;

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

    public Device objDtoToObj(DeviceDto deviceDto, Device device) {
        device.setId(deviceDto.getId());
        device.setBrand(deviceDto.getBrand());
        device.setName(deviceDto.getName());
        device.setPrice(deviceDto.getPrice());
        device.setProvider(getProvider(deviceDto.getProviderId()));
        device.setStore(getStore(deviceDto.getStoreId()));
        return device;
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
