package vapeShop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import vapeShop.dto.EvaporatorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vapeShop.service.EvaporatorService;
import vapeShop.service.ProviderService;
import vapeShop.service.StoreService;

import java.util.List;

@Controller
@RequestMapping("/evaporator")
@RequiredArgsConstructor
public class EvaporatorController {

    private final EvaporatorService evaporatorService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<EvaporatorDto> evaporatorDtoList = evaporatorService.findAllEvaporators();
        model.addAttribute("evaporators", evaporatorDtoList);
        return "evaporator/evaporators";
    }

    @GetMapping("/create")
    public String creationEvaporator(Model model) {
        model.addAttribute("evaporator", new EvaporatorDto());
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "evaporator/add";
    }

    @PostMapping()
    public String createEvaporator(
            @ModelAttribute("evaporator") @Valid EvaporatorDto evaporatorDto,
            BindingResult bindingResult,
            @RequestParam(value = "store_id", required = false) Long store_id,
            @RequestParam(value = "provider_id", required = false) Long provider_id) {
        if (bindingResult.hasErrors()){return "evaporator/add";}

        evaporatorDto.setProviderId(provider_id);
        evaporatorDto.setStoreId(store_id);
        evaporatorService.createEvaporator(evaporatorDto);

        return "redirect:/evaporator";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("evaporator", evaporatorService.findEvaporatorById(id));
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "evaporator/edit";
    }

    @PatchMapping("/{id}")
    public String updateEvaporator(@ModelAttribute("evaporator") @Valid EvaporatorDto evaporatorDto,
                                   BindingResult bindingResult,
                                   @RequestParam(value = "store_id", required = false) Long store_id,
                                   @RequestParam(value = "provider_id", required = false) Long provider_id) {
        if (bindingResult.hasErrors()){return "evaporator/edit";}

        evaporatorDto.setProviderId(provider_id);
        evaporatorDto.setStoreId(store_id);
        evaporatorService.updateEvaporator(evaporatorDto);
        return "redirect:/evaporator";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        evaporatorService.deleteEvaporator(id);
        return  "redirect:/evaporator";
    }
}
