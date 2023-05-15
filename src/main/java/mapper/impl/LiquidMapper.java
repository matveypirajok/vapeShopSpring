package mapper.impl;

import dto.LiquidDto;
import entity.Liquid;
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

    public Liquid objDtoToObj(LiquidDto liquidDto, Liquid liquid) {
        liquid.setId(liquidDto.getId());
        liquid.setName(liquidDto.getName());
        liquid.setTaste(liquidDto.getTaste());
        liquid.setPrice(liquidDto.getPrice());
        liquid.setProvider(getProvider(liquidDto.getProviderId()));
        liquid.setStore(getStore(liquidDto.getStoreId()));
        return liquid;
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
