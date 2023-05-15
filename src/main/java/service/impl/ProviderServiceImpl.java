package service.impl;

import dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import mapper.impl.ProviderMapper;
import org.springframework.stereotype.Service;
import repositories.ProviderRep;
import service.ProviderService;

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
    public Optional<ProviderDto> findProviderById(Long id) {
        return providerRep.findById(id)
                .map(providerMapper::mapToDTO);
    }
}
