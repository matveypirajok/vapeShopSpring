package vapeShop.mapper.impl;

import vapeShop.dto.EvaporatorDto;
import vapeShop.entity.Evaporator;
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
public class EvaporatorMapper implements Mapper<Evaporator, EvaporatorDto> {
    private final StoreRep storeRep;
    private final ProviderRep providerRep;

    @Override
    public Evaporator mapToEntity(EvaporatorDto object) {
        return Evaporator.builder()
                .id(object.getId())
                .resistance(object.getResistance())
                .price(object.getPrice())
                .name(object.getName())
                .provider(getProvider(object.getProviderId()))
                .store(getStore(object.getStoreId()))
                .build();
    }

    @Override
    public EvaporatorDto mapToDTO(Evaporator object) {
        return EvaporatorDto.builder()
                .id(object.getId())
                .name(object.getName())
                .price(object.getPrice())
                .resistance(object.getResistance())
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
