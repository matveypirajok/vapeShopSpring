package vapeShop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import vapeShop.dto.LiquidDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vapeShop.service.LiquidService;
import vapeShop.service.ProviderService;
import vapeShop.service.StoreService;

import java.util.List;

@Controller
@RequestMapping("/liquid")
@RequiredArgsConstructor
public class LiquidController {

    private final LiquidService liquidService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<LiquidDto> liquidsDtoList = liquidService.findAllLiquids();
        model.addAttribute("liquids", liquidsDtoList);
        return "liquid/liquids";
    }

    @GetMapping("/create")
    public String creationLiquid(Model model) {
        model.addAttribute("liquid", new LiquidDto());
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "liquid/add";
    }

    @PostMapping()
    public String createLiquid(
            @ModelAttribute("evaporator") @Valid LiquidDto liquidDto,
            BindingResult bindingResult,
            @RequestParam(value = "store_id", required = false) Long store_id,
            @RequestParam(value = "provider_id", required = false) Long provider_id) {
        if (bindingResult.hasErrors()){return "liquid/add";}

        liquidDto.setProviderId(provider_id);
        liquidDto.setStoreId(store_id);
        liquidService.createLiquid(liquidDto);

        return "redirect:/liquid";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("liquid", liquidService.findLiquidById(id));
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "liquid/edit";
    }

    @PatchMapping("/{id}")
    public String updateLiquid(@ModelAttribute("evaporator") LiquidDto liquidDto,
                               BindingResult bindingResult,
                               @RequestParam(value = "store_id", required = false) Long store_id,
                               @RequestParam(value = "provider_id", required = false) Long provider_id) {
        if (bindingResult.hasErrors()){return "liquid/edit";}

        liquidDto.setProviderId(provider_id);
        liquidDto.setStoreId(store_id);
        liquidService.updateLiquid(liquidDto);
        return "redirect:/liquid";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        liquidService.deleteLiquid(id);
        return  "redirect:/liquid";
    }
}
