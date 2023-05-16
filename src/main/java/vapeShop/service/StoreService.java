package vapeShop.service;

import vapeShop.dto.StoreDto;

import java.util.List;

public interface StoreService {

    void createStore(StoreDto storeDto);

    List<StoreDto> findAllStores();

    void updateStore(StoreDto storeDto);

    boolean deleteStore(Long id);

    StoreDto findStoreById(Long id);
}
