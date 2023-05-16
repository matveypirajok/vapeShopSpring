package vapeShop.service;

import vapeShop.dto.AccessoryDto;

import java.util.List;

public interface AccessoryService {

    void createAccessory(AccessoryDto accessoryDto);

    List<AccessoryDto> findAllAccessories();

    void updateAccessory(AccessoryDto accessoryDto);

    boolean deleteAccessory(Long id);

    AccessoryDto findAccessoryById(Long id);
}
