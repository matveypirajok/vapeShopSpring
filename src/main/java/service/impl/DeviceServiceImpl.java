package service.impl;

import dto.DeviceDto;
import lombok.RequiredArgsConstructor;
import mapper.impl.DeviceMapper;
import org.springframework.stereotype.Service;
import repositories.DeviceRep;
import service.DeviceService;

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
    public Optional<DeviceDto> findDeviceById(Long id) {
        return deviceRep.findById(id)
                .map(deviceMapper::mapToDTO);
    }
}
