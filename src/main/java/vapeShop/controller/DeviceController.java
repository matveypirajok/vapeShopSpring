package vapeShop.controller;

import jakarta.validation.Valid;
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

import static vapeShop.data.ControllerData.*;
import static vapeShop.data.EntityData.STORE_ID;
import static vapeShop.data.EntityData.PROVIDER_ID;

@Controller
@RequestMapping(MAPPING_DEVICE)
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<DeviceDto> deviceDtoList = deviceService.findAllDevices();
        model.addAttribute(DEVICES_LIST, deviceDtoList);
        return TO_DEVICES;
    }

    @GetMapping(MAPPING_CREATE)
    public String creationDevice(Model model) {
        model.addAttribute(DEVICE_DTO, new DeviceDto());
        model.addAttribute(PROVIDERS_LIST, providerService.findAllProviders());
        model.addAttribute(STORES_LIST, storeService.findAllStores());
        return TO_DEVICE_CREATE;
    }

    @PostMapping()
    public String createDevice(
            @ModelAttribute(DEVICE_DTO) DeviceDto deviceDto,
            BindingResult bindingResult,
            @RequestParam(value = STORE_ID, required = false) Long storeId,
            @RequestParam(value = PROVIDER_ID, required = false) Long providerId)
    {
        if (bindingResult.hasErrors()){return TO_DEVICE_CREATE;}
        deviceDto.setProviderId(providerId);
        deviceDto.setStoreId(storeId);
        deviceService.createDevice(deviceDto);

        return REDIRECT_DEVICE;
    }

    @GetMapping(MAPPING_EDIT)
    public String update(@PathVariable(ID) Long id,
                         Model model) {
        model.addAttribute(DEVICE_DTO, deviceService.findDeviceById(id));
        model.addAttribute(PROVIDERS_LIST, providerService.findAllProviders());
        model.addAttribute(STORES_LIST, storeService.findAllStores());
        return TO_DEVICE_EDIT;
    }

    @PatchMapping(MAPPING_ID)
    public String updateDevice(@ModelAttribute(DEVICE_DTO) @Valid DeviceDto deviceDto,
                               BindingResult bindingResult,
                               @RequestParam(value = STORE_ID, required = false) Long storeId,
                               @RequestParam(value = PROVIDER_ID, required = false) Long providerId) {
        if (bindingResult.hasErrors()){return TO_DEVICE_EDIT;}
        deviceDto.setProviderId(providerId);
        deviceDto.setStoreId(storeId);
        deviceService.updateDevice(deviceDto);
        return REDIRECT_DEVICE;
    }

    @PostMapping(MAPPING_ID)
    public String delete(@PathVariable(ID) Long id)
    {
        deviceService.deleteDevice(id);
        return  REDIRECT_DEVICE;
    }
}
