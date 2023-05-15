package mapper.impl;

import dto.EvaporatorDto;
import entity.Evaporator;
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

    public Evaporator objDtoToObj(EvaporatorDto evaporatorDto, Evaporator evaporator) {
        evaporator.setId(evaporatorDto.getId());
        evaporator.setName(evaporatorDto.getName());
        evaporator.setPrice(evaporatorDto.getPrice());
        evaporator.setResistance(evaporatorDto.getResistance());
        evaporator.setProvider(getProvider(evaporatorDto.getProviderId()));
        evaporator.setStore(getStore(evaporatorDto.getStoreId()));
        return evaporator;
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
