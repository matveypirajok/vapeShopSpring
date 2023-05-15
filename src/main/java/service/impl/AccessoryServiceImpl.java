package service.impl;

import dto.AccessoryDto;
import entity.Accessory;
import lombok.RequiredArgsConstructor;
import mapper.impl.AccessoryMapper;
import org.springframework.stereotype.Service;
import repositories.AccessoryRep;
import service.AccessoryService;

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
    public Optional<AccessoryDto> findAccessoryById(Long id) {
        return accessoryRep.findById(id)
                .map(accessoryMapper::mapToDTO);
    }
}
