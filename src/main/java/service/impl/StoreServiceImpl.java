package service.impl;

import dto.StoreDto;
import lombok.RequiredArgsConstructor;
import mapper.impl.StoreMapper;
import org.springframework.stereotype.Service;
import repositories.StoreRep;
import service.StoreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreMapper storeMapper;
    private final StoreRep storeRep;

    @Override
    public void createStore(StoreDto storeDto) {
        storeRep.save(storeMapper.mapToEntity(storeDto));
    }

    @Override
    public List<StoreDto> findAllStores() {
        return storeRep.findAll().stream().map(storeMapper::mapToDTO).toList();
    }

    @Override
    public void updateStore(StoreDto storeDto) {
        storeRep.save(storeMapper.mapToEntity(storeDto));
    }

    @Override
    public boolean deleteStore(Long id) {
        return storeRep.findById(id)
                .map(entity -> {
                    storeRep.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<StoreDto> findStoreById(Long id) {
        return storeRep.findById(id)
                .map(storeMapper::mapToDTO);
    }
}
