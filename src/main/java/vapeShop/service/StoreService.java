package vapeShop.service;

import vapeShop.dto.StoreDto;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    void createStore(StoreDto storeDto);

    List<StoreDto> findAllStores();

    void updateStore(StoreDto storeDto);

    boolean deleteStore(Long id);

    StoreDto findStoreById(Long id);
}
