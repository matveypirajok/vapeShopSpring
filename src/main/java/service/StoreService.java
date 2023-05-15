package service;

import dto.StoreDto;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    void createStore(StoreDto storeDto);

    List<StoreDto> findAllStores();

    void updateStore(StoreDto storeDto);

    boolean deleteStore(Long id);

    Optional<StoreDto> findStoreById(Long id);
}
