package vapeShop.service;

import vapeShop.dto.ProviderDto;

import java.util.List;
import java.util.Optional;

public interface ProviderService {

    void createProvider(ProviderDto providerDto);

    List<ProviderDto> findAllProviders();

    void updateProvider(ProviderDto providerDto);

    boolean deleteProvider(Long id);

    ProviderDto findProviderById(Long id);
}
