package vapeShop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/accessory")
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<AccessoryDto> accessoryDtoList = accessoryService.findAllAccessories();
        model.addAttribute("accessories", accessoryDtoList);
        return "accessory/accessories";
    }

    @GetMapping("/create")
    public String creationAccessory(Model model) {
        model.addAttribute("accessory", new AccessoryDto());
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "accessory/add";
    }

    @PostMapping()
    public String createAccessory(
            @ModelAttribute("accessory") @Valid AccessoryDto accessoryDto,
            BindingResult bindingResult,
            @RequestParam(value = "store_id", required = false) Long store_id,
            @RequestParam(value = "provider_id", required = false) Long provider_id
    ) {
        if (bindingResult.hasErrors()) {
            return "accessory/add";
        }
        accessoryDto.setProviderId(provider_id);
        accessoryDto.setStoreId(store_id);
        accessoryService.createAccessory(accessoryDto);
        return "redirect:/accessory";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("accessory", accessoryService.findAccessoryById(id));
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "accessory/edit";
    }

    @PatchMapping("/{id}")
    public String updateAccessory(@ModelAttribute("accessory") @Valid AccessoryDto accessoryDto,
                                  BindingResult bindingResult,
                                  @RequestParam(value = "store_id", required = false) Long store_id,
                                  @RequestParam(value = "provider_id", required = false) Long provider_id) {
        if (bindingResult.hasErrors()) {
            return "accessory/edit";
        }
        accessoryDto.setProviderId(provider_id);
        accessoryDto.setStoreId(store_id);
        accessoryService.updateAccessory(accessoryDto);
        return "redirect:/accessory";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        accessoryService.deleteAccessory(id);
        return "redirect:/accessory";
    }
}
