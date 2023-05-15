package controller;

import dto.DeviceDto;
import dto.EvaporatorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.DeviceService;
import service.EvaporatorService;
import service.ProviderService;
import service.StoreService;

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

    @PostMapping("/create")
    public String creationEvaporator(Model model) {
        model.addAttribute("evaporator", new EvaporatorDto());
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "evaporator/add";
    }

    @PostMapping()
    public String createEvaporator(
            @ModelAttribute("evaporator") EvaporatorDto evaporatorDto,
            @RequestParam(value = "store_id", required = false) Long store_id,
            @RequestParam(value = "provider_id", required = false) Long provider_id) {
        evaporatorDto.setProviderId(provider_id);
        evaporatorDto.setProviderId(store_id);
        evaporatorService.createEvaporator(evaporatorDto);

        return "redirect:/evaporator";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("evaporator", evaporatorService.findEvaporatorById(id));
        return "evaporator/edit";
    }

    @PatchMapping("/{id}")
    public String updateEvaporator(@ModelAttribute("evaporator") EvaporatorDto evaporatorDto) {
        evaporatorService.updateEvaporator(evaporatorDto);
        return "redirect:/evaporator";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        evaporatorService.deleteEvaporator(id);
        return  "redirect:/evaporator";
    }
}
