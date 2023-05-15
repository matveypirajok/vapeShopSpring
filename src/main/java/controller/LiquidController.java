package controller;

import dto.EvaporatorDto;
import dto.LiquidDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.EvaporatorService;
import service.LiquidService;
import service.ProviderService;
import service.StoreService;

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

    @PostMapping("/create")
    public String creationLiquid(Model model) {
        model.addAttribute("liquid", new LiquidDto());
        model.addAttribute("providers", providerService.findAllProviders());
        model.addAttribute("stores", storeService.findAllStores());
        return "liquid/add";
    }

    @PostMapping()
    public String createLiquid(
            @ModelAttribute("evaporator") LiquidDto liquidDto,
            @RequestParam(value = "store_id", required = false) Long store_id,
            @RequestParam(value = "provider_id", required = false) Long provider_id) {
        liquidDto.setProviderId(provider_id);
        liquidDto.setProviderId(store_id);
        liquidService.createLiquid(liquidDto);

        return "redirect:/liquid";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("liquid", liquidService.findLiquidById(id));
        return "liquid/edit";
    }

    @PatchMapping("/{id}")
    public String updateLiquid(@ModelAttribute("evaporator") LiquidDto liquidDto) {
        liquidService.updateLiquid(liquidDto);
        return "redirect:/liquid";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        liquidService.deleteLiquid(id);
        return  "redirect:/liquid";
    }
}
