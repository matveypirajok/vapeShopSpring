package controller;

import dto.AccessoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.AccessoryService;
import service.ProviderService;
import service.StoreService;

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

    @PostMapping("/create")
    public String creationAccessory(Model model) {
        model.addAttribute("accessory", new AccessoryDto());
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "accessory/add";
    }

    @PostMapping()
    public String createAccessory(
            @ModelAttribute("accessory") AccessoryDto accessoryDto,
            @RequestParam(value = "store_id", required = false) Long store_id,
            @RequestParam(value = "provider_id", required = false) Long provider_id
    ) {
        accessoryDto.setProviderId(provider_id);
        accessoryDto.setProviderId(store_id);
        accessoryService.createAccessory(accessoryDto);

        return "redirect:/accessory";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("accessory", accessoryService.findAccessoryById(id));
        return "accessory/edit";
    }

    @PatchMapping("/{id}")
    public String updateAccessory(@ModelAttribute("accessory") AccessoryDto accessoryDto) {
        accessoryService.updateAccessory(accessoryDto);
        return "redirect:/accessory";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        accessoryService.deleteAccessory(id);
        return  "redirect:/accessory";
    }
}
