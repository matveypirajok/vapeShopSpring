package vapeShop.service;

import vapeShop.dto.DeviceDto;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    void createDevice(DeviceDto deviceDto);

    List<DeviceDto> findAllDevices();

    void updateDevice(DeviceDto deviceDto);

    boolean deleteDevice(Long id);

    DeviceDto findDeviceById(Long id);
}
