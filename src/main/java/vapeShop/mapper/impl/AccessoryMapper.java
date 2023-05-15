package vapeShop.mapper.impl;

import vapeShop.dto.AccessoryDto;
import vapeShop.entity.Accessory;
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
public class AccessoryMapper implements Mapper<Accessory, AccessoryDto> {
    private final StoreRep storeRep;
    private final ProviderRep providerRep;

    @Override
    public Accessory mapToEntity(AccessoryDto object) {
        return Accessory.builder()
                .id(object.getId())
                .name(object.getName())
                .type(object.getType())
                .price(object.getPrice())
                .provider(getProvider(object.getProviderId()))
                .store(getStore(object.getStoreId()))
                .build();
    }

    @Override
    public AccessoryDto mapToDTO(Accessory object) {
        return AccessoryDto.builder()
                .id(object.getId())
                .name(object.getName())
                .providerId(object.getProvider().getId())
                .storeId(object.getStore().getId())
                .price(object.getPrice())
                .providerName(object.getProvider().getName())
                .storeName(object.getStore().getName())
                .type(object.getType())
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
