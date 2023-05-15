package vapeShop.mapper.impl;

import vapeShop.dto.LiquidDto;
import vapeShop.entity.Liquid;
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
public class LiquidMapper implements Mapper<Liquid, LiquidDto> {
    private final StoreRep storeRep;
    private final ProviderRep providerRep;

    @Override
    public Liquid mapToEntity(LiquidDto object) {
        return Liquid.builder()
                .id(object.getId())
                .name(object.getName())
                .price(object.getPrice())
                .taste(object.getTaste())
                .provider(getProvider(object.getProviderId()))
                .store(getStore(object.getStoreId()))
                .build();
    }

    @Override
    public LiquidDto mapToDTO(Liquid object) {
        return LiquidDto.builder()
                .id(object.getId())
                .name(object.getName())
                .price(object.getPrice())
                .taste(object.getTaste())
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
