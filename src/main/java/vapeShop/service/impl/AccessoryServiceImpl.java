package vapeShop.service.impl;

import vapeShop.dto.AccessoryDto;
import lombok.RequiredArgsConstructor;
import vapeShop.entity.Accessory;
import vapeShop.mapper.impl.AccessoryMapper;
import org.springframework.stereotype.Service;
import vapeShop.repositories.AccessoryRep;
import vapeShop.service.AccessoryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRep accessoryRep;
    private final AccessoryMapper accessoryMapper;

    @Override
    public void createAccessory(AccessoryDto accessoryDto) {

        accessoryRep.save(accessoryMapper.mapToEntity(accessoryDto));
    }

    @Override
    public List<AccessoryDto> findAllAccessories() {

        return accessoryRep.findAll().stream().map(accessoryMapper::mapToDTO).toList();
    }

    @Override
    public void updateAccessory(AccessoryDto accessoryDto) {
        accessoryRep.save(accessoryMapper.mapToEntity(accessoryDto));
    }

    @Override
    public boolean deleteAccessory(Long id) {
        return accessoryRep.findById(id)
                .map(entity -> {
                    accessoryRep.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public AccessoryDto findAccessoryById(Long id) {
        Optional<Accessory> accessory = accessoryRep.findById(id);

        return accessoryMapper.mapToDTO(accessory.orElseThrow());
    }
}
