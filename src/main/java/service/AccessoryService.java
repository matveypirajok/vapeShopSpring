package service;

import dto.AccessoryDto;
import entity.Accessory;

import java.util.List;
import java.util.Optional;

public interface AccessoryService {

    void createAccessory(AccessoryDto accessoryDto);

    List<AccessoryDto> findAllAccessories();

    void updateAccessory(AccessoryDto accessoryDto);

    boolean deleteAccessory(Long id);

    Optional<AccessoryDto> findAccessoryById(Long id);
}
