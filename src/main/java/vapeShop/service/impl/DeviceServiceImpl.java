package vapeShop.service.impl;

import vapeShop.dto.DeviceDto;
import lombok.RequiredArgsConstructor;
import vapeShop.entity.Device;
import vapeShop.mapper.impl.DeviceMapper;
import org.springframework.stereotype.Service;
import vapeShop.repositories.DeviceRep;
import vapeShop.service.DeviceService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceMapper deviceMapper;
    private final DeviceRep deviceRep;


    @Override
    public void createDevice(DeviceDto deviceDto) {
        deviceRep.save(deviceMapper.mapToEntity(deviceDto));
    }

    @Override
    public List<DeviceDto> findAllDevices() {
        return deviceRep.findAll().stream().map(deviceMapper::mapToDTO).toList();
    }

    @Override
    public void updateDevice(DeviceDto deviceDto) {
        deviceRep.save(deviceMapper.mapToEntity(deviceDto));
    }

    @Override
    public boolean deleteDevice(Long id) {
        return deviceRep.findById(id)
                .map(entity -> {
                    deviceRep.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public DeviceDto findDeviceById(Long id) {
        Optional<Device> device = deviceRep.findById(id);

        return deviceMapper.mapToDTO(device.orElseThrow());
    }
}
