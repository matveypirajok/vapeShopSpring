package controller;

import dto.AccessoryDto;
import dto.CartridgeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.AccessoryService;
import service.CartridgeService;
import service.ProviderService;
import service.StoreService;

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

    @PostMapping("/create")
    public String creationCartridge(Model model) {
        model.addAttribute("cartridge", new CartridgeDto());
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "cartridge/add";
    }

    @PostMapping()
    public String createCartridge(
            @ModelAttribute("accessory") CartridgeDto cartridgeDto,
            @RequestParam(value = "store_id", required = false) Long store_id,
            @RequestParam(value = "provider_id", required = false) Long provider_id
    ) {
        cartridgeDto.setProviderId(provider_id);
        cartridgeDto.setProviderId(store_id);
        cartridgeService.createCartridge(cartridgeDto);

        return "redirect:/cartridge";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("cartridge", cartridgeService.findCartridgeById(id));
        return "cartridge/edit";
    }

    @PatchMapping("/{id}")
    public String updateCartridge(@ModelAttribute("cartridge") CartridgeDto cartridgeDto) {
        cartridgeService.updateCartridge(cartridgeDto);
        return "redirect:/cartridge";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        cartridgeService.deleteCartridge(id);
        return  "redirect:/cartridge";
    }
}
