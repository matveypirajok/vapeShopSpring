package vapeShop.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import vapeShop.dto.AccessoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vapeShop.service.AccessoryService;
import vapeShop.service.ProviderService;
import vapeShop.service.StoreService;

import java.util.List;

import static vapeShop.data.ControllerData.MAPPING_CREATE;
import static vapeShop.data.ControllerData.ACCESSORY_DTO;
import static vapeShop.data.ControllerData.ACCESSORIES_LIST;
import static vapeShop.data.ControllerData.STORES_LIST;
import static vapeShop.data.ControllerData.PROVIDERS_LIST;
import static vapeShop.data.ControllerData.ID;
import static vapeShop.data.ControllerData.TO_ACCESSORIES;
import static vapeShop.data.ControllerData.TO_ACCESSORY_CREATE;
import static vapeShop.data.ControllerData.TO_ACCESSORY_EDIT;
import static vapeShop.data.ControllerData.REDIRECT_ACCESSORY;
import static vapeShop.data.ControllerData.MAPPING_EDIT;
import static vapeShop.data.ControllerData.MAPPING_ID;
import static vapeShop.data.EntityData.STORE_ID;
import static vapeShop.data.EntityData.PROVIDER_ID;
import static vapeShop.data.ControllerData.MAPPING_ACCESSORY;

@Controller
@RequestMapping(MAPPING_ACCESSORY)
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<AccessoryDto> accessoryDtoList = accessoryService.findAllAccessories();
        model.addAttribute(ACCESSORIES_LIST, accessoryDtoList);
        return TO_ACCESSORIES;
    }

    @GetMapping(MAPPING_CREATE)
    public String creationAccessory(Model model) {
        model.addAttribute(ACCESSORY_DTO, new AccessoryDto());
        model.addAttribute(PROVIDERS_LIST, providerService.findAllProviders());
        model.addAttribute(STORES_LIST, storeService.findAllStores());
        return TO_ACCESSORY_CREATE;
    }

    @PostMapping()
    public String createAccessory(
            @ModelAttribute(ACCESSORY_DTO) @Valid AccessoryDto accessoryDto,
            BindingResult bindingResult,
            @RequestParam(value = STORE_ID, required = false) Long store_id,
            @RequestParam(value = PROVIDER_ID, required = false) Long provider_id
    ) {
        if (bindingResult.hasErrors()) {
            return TO_ACCESSORY_CREATE;
        }
        accessoryDto.setProviderId(provider_id);
        accessoryDto.setStoreId(store_id);
        accessoryService.createAccessory(accessoryDto);
        return REDIRECT_ACCESSORY;
    }

    @GetMapping(MAPPING_EDIT)
    public String update(@PathVariable(ID) Long id,
                         Model model) {
        model.addAttribute(ACCESSORY_DTO, accessoryService.findAccessoryById(id));
        model.addAttribute(PROVIDERS_LIST, providerService.findAllProviders());
        model.addAttribute(STORES_LIST, storeService.findAllStores());
        return TO_ACCESSORY_EDIT;
    }

    @PatchMapping(MAPPING_ID)
    public String updateAccessory(@ModelAttribute(ACCESSORY_DTO) @Valid AccessoryDto accessoryDto,
                                  BindingResult bindingResult,
                                  @RequestParam(value = STORE_ID, required = false) Long store_id,
                                  @RequestParam(value = PROVIDER_ID, required = false) Long provider_id) {
        if (bindingResult.hasErrors()) {
            return TO_ACCESSORY_EDIT;
        }
        accessoryDto.setProviderId(provider_id);
        accessoryDto.setStoreId(store_id);
        accessoryService.updateAccessory(accessoryDto);
        return REDIRECT_ACCESSORY;
    }

    @PostMapping(MAPPING_ID)
    public String delete(@PathVariable(ID) Long id) {
        accessoryService.deleteAccessory(id);
        return REDIRECT_ACCESSORY;
    }
}
