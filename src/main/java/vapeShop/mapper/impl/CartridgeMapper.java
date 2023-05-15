package vapeShop.mapper.impl;

import vapeShop.dto.CartridgeDto;
import vapeShop.entity.Cartridge;
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
public class CartridgeMapper implements Mapper<Cartridge, CartridgeDto> {
    private final StoreRep storeRep;
    private final ProviderRep providerRep;

    @Override
    public Cartridge mapToEntity(CartridgeDto object) {
        return Cartridge.builder()
                .id(object.getId())
                .resistance(object.getResistance())
                .name(object.getName())
                .price(object.getPrice())
                .provider(getProvider(object.getProviderId()))
                .store(getStore(object.getStoreId()))
                .build();
    }

    @Override
    public CartridgeDto mapToDTO(Cartridge object) {
        return CartridgeDto.builder()
                .id(object.getId())
                .providerId(object.getProvider().getId())
                .name(object.getName())
                .providerName(object.getProvider().getName())
                .storeId(object.getStore().getId())
                .price(object.getPrice())
                .resistance(object.getResistance())
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
