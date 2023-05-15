package controller;

import dto.CartridgeDto;
import dto.DeviceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CartridgeService;
import service.DeviceService;
import service.ProviderService;
import service.StoreService;

import java.util.List;

@Controller
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<DeviceDto> deviceDtoList = deviceService.findAllDevices();
        model.addAttribute("devices", deviceDtoList);
        return "device/devices";
    }

    @PostMapping("/create")
    public String creationDevice(Model model) {
        model.addAttribute("devices", new DeviceDto());
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "device/add";
    }

    @PostMapping()
    public String createDevice(
            @ModelAttribute("accessory") DeviceDto deviceDto,
            @RequestParam(value = "store_id", required = false) Long store_id,
            @RequestParam(value = "provider_id", required = false) Long provider_id)
    {
        deviceDto.setProviderId(provider_id);
        deviceDto.setProviderId(store_id);
        deviceService.createDevice(deviceDto);

        return "redirect:/devices";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("device", deviceService.findDeviceById(id));
        return "device/edit";
    }

    @PatchMapping("/{id}")
    public String updateDevice(@ModelAttribute("device") DeviceDto deviceDto) {
        deviceService.updateDevice(deviceDto);
        return "redirect:/devices";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        deviceService.deleteDevice(id);
        return  "redirect:/devices";
    }
}
