package vapeShop.service.impl;

import vapeShop.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import vapeShop.entity.Provider;
import vapeShop.mapper.impl.ProviderMapper;
import org.springframework.stereotype.Service;
import vapeShop.repositories.ProviderRep;
import vapeShop.service.ProviderService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderMapper providerMapper;
    private final ProviderRep providerRep;

    @Override
    public void createProvider(ProviderDto providerDto) {
        providerRep.save(providerMapper.mapToEntity(providerDto));
    }

    @Override
    public List<ProviderDto> findAllProviders() {
        return providerRep.findAll().stream().map(providerMapper::mapToDTO).toList();
    }

    @Override
    public void updateProvider(ProviderDto providerDto) {
        providerRep.save(providerMapper.mapToEntity(providerDto));
    }

    @Override
    public boolean deleteProvider(Long id) {
        return providerRep.findById(id)
                .map(entity -> {
                    providerRep.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public ProviderDto findProviderById(Long id) {
        Optional<Provider> provider = providerRep.findById(id);

        return providerMapper.mapToDTO(provider.orElseThrow());
    }
}
