package service;

import dto.DeviceDto;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    void createDevice(DeviceDto deviceDto);

    List<DeviceDto> findAllDevices();

    void updateDevice(DeviceDto deviceDto);

    boolean deleteDevice(Long id);

    Optional<DeviceDto> findDeviceById(Long id);
}
