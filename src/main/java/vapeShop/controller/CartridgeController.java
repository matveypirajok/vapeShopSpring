package vapeShop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/cartridge")
@RequiredArgsConstructor
public class CartridgeController {

    private final CartridgeService cartridgeService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<CartridgeDto> deviceDtoList = cartridgeService.findAllCartridges();
        model.addAttribute("cartridges", deviceDtoList);
        return "cartridge/cartridges";
    }

    @GetMapping("/create")
    public String creationCartridge(Model model) {
        model.addAttribute("cartridge", new CartridgeDto());
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "cartridge/add";
    }

    @PostMapping()
    public String createCartridge(
            @ModelAttribute("accessory") @Valid CartridgeDto cartridgeDto,
            BindingResult bindingResult,
            @RequestParam(value = "store_id", required = false) Long store_id,
            @RequestParam(value = "provider_id", required = false) Long provider_id
    ) {
        if (bindingResult.hasErrors()) {
            return "cartridge/add";
        }

        cartridgeDto.setProviderId(provider_id);
        cartridgeDto.setStoreId(store_id);
        cartridgeService.createCartridge(cartridgeDto);

        return "redirect:/cartridge";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("cartridge", cartridgeService.findCartridgeById(id));
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "cartridge/edit";
    }

    @PatchMapping("/{id}")
    public String updateCartridge(@ModelAttribute("cartridge") @Valid CartridgeDto cartridgeDto,
                                  BindingResult bindingResult,
                                  @RequestParam(value = "store_id", required = false) Long store_id,
                                  @RequestParam(value = "provider_id", required = false) Long provider_id) {
        if (bindingResult.hasErrors()) {
            return "cartridge/edit";
        }

        cartridgeDto.setProviderId(provider_id);
        cartridgeDto.setStoreId(store_id);
        cartridgeService.updateCartridge(cartridgeDto);
        return "redirect:/cartridge";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        cartridgeService.deleteCartridge(id);
        return "redirect:/cartridge";
    }
}
