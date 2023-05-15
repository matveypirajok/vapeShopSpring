package vapeShop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import vapeShop.dto.DeviceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vapeShop.service.DeviceService;
import vapeShop.service.ProviderService;
import vapeShop.service.StoreService;

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

    @GetMapping("/create")
    public String creationDevice(Model model) {
        model.addAttribute("device", new DeviceDto());
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "device/add";
    }

    @PostMapping()
    public String createDevice(
            @ModelAttribute("device") DeviceDto deviceDto,
            BindingResult bindingResult,
            @RequestParam(value = "store_id", required = false) Long store_id,
            @RequestParam(value = "provider_id", required = false) Long provider_id)
    {
        if (bindingResult.hasErrors()){return "device/add";}
        deviceDto.setProviderId(provider_id);
        deviceDto.setStoreId(store_id);
        deviceService.createDevice(deviceDto);

        return "redirect:/devices";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("device", deviceService.findDeviceById(id));
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "device/edit";
    }

    @PatchMapping("/{id}")
    public String updateDevice(@ModelAttribute("device") @Valid DeviceDto deviceDto,
                               BindingResult bindingResult,
                               @RequestParam(value = "store_id", required = false) Long store_id,
                               @RequestParam(value = "provider_id", required = false) Long provider_id) {
        if (bindingResult.hasErrors()){return "device/edit";}
        deviceDto.setProviderId(provider_id);
        deviceDto.setStoreId(store_id);
        deviceService.updateDevice(deviceDto);
        return "redirect:/devices";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        deviceService.deleteDevice(id);
        return  "redirect:/devices";
    }
}
