package mapper.impl;

import dto.CartridgeDto;
import entity.Cartridge;
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

    public Cartridge objDtoToObj(CartridgeDto cartridgeDto, Cartridge cartridge) {
        cartridge.setId(cartridgeDto.getId());
        cartridge.setName(cartridgeDto.getName());
        cartridge.setPrice(cartridgeDto.getPrice());
        cartridge.setResistance(cartridgeDto.getResistance());
        cartridge.setProvider(getProvider(cartridgeDto.getProviderId()));
        cartridge.setStore(getStore(cartridgeDto.getStoreId()));
        return cartridge;
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
