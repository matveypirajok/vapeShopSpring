package vapeShop.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import vapeShop.dto.CartridgeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vapeShop.service.CartridgeService;
import vapeShop.service.ProviderService;
import vapeShop.service.StoreService;

import java.util.List;

import static vapeShop.data.ControllerData.*;
import static vapeShop.data.EntityData.STORE_ID;
import static vapeShop.data.EntityData.PROVIDER_ID;

@Controller
@RequestMapping(MAPPING_CARTRIDGE)
@RequiredArgsConstructor
public class CartridgeController {

    private final CartridgeService cartridgeService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<CartridgeDto> deviceDtoList = cartridgeService.findAllCartridges();
        model.addAttribute(CARTRIDGES_LIST, deviceDtoList);
        return TO_CARTRIDGES;
    }

    @GetMapping(MAPPING_CREATE)
    public String creationCartridge(Model model) {
        model.addAttribute(CARTRIDGE_DTO, new CartridgeDto());
        model.addAttribute(PROVIDERS_LIST, providerService.findAllProviders());
        model.addAttribute(STORES_LIST, storeService.findAllStores());
        return TO_CARTRIDGE_CREATE;
    }

    @PostMapping()
    public String createCartridge(
            @ModelAttribute(CARTRIDGE_DTO) @Valid CartridgeDto cartridgeDto,
            BindingResult bindingResult,
            @RequestParam(value = STORE_ID, required = false) Long storeId,
            @RequestParam(value = PROVIDER_ID, required = false) Long providerId
    ) {
        if (bindingResult.hasErrors()) {
            return TO_CARTRIDGE_CREATE;
        }

        cartridgeDto.setProviderId(providerId);
        cartridgeDto.setStoreId(storeId);
        cartridgeService.createCartridge(cartridgeDto);

        return REDIRECT_CARTRIDGE;
    }

    @GetMapping(MAPPING_EDIT)
    public String update(@PathVariable(ID) Long id,
                         Model model) {
        model.addAttribute(CARTRIDGE_DTO, cartridgeService.findCartridgeById(id));
        model.addAttribute(PROVIDERS_LIST, providerService.findAllProviders());
        model.addAttribute(STORES_LIST, storeService.findAllStores());
        return TO_CARTRIDGE_EDIT;
    }

    @PatchMapping(MAPPING_ID)
    public String updateCartridge(@ModelAttribute(CARTRIDGE_DTO) @Valid CartridgeDto cartridgeDto,
                                  BindingResult bindingResult,
                                  @RequestParam(value = STORE_ID, required = false) Long storeId,
                                  @RequestParam(value = PROVIDER_ID, required = false) Long providerId) {
        if (bindingResult.hasErrors()) {
            return TO_CARTRIDGE_EDIT;
        }

        cartridgeDto.setProviderId(providerId);
        cartridgeDto.setStoreId(storeId);
        cartridgeService.updateCartridge(cartridgeDto);
        return REDIRECT_CARTRIDGE;
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        cartridgeService.deleteCartridge(id);
        return REDIRECT_CARTRIDGE;
    }
}
