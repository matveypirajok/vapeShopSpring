package vapeShop.controller;

import jakarta.validation.Valid;
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

import static vapeShop.data.ControllerData.*;
import static vapeShop.data.EntityData.STORE_ID;
import static vapeShop.data.EntityData.PROVIDER_ID;

@Controller
@RequestMapping(MAPPING_LIQUID)
@RequiredArgsConstructor
public class LiquidController {

    private final LiquidService liquidService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<LiquidDto> liquidsDtoList = liquidService.findAllLiquids();
        model.addAttribute(LIQUIDS_LIST, liquidsDtoList);
        return TO_LIQUIDS;
    }

    @GetMapping(MAPPING_CREATE)
    public String creationLiquid(Model model) {
        model.addAttribute(LIQUID_DTO, new LiquidDto());
        model.addAttribute(PROVIDERS_LIST, providerService.findAllProviders());
        model.addAttribute(STORES_LIST, storeService.findAllStores());
        return TO_LIQUID_CREATE;
    }

    @PostMapping()
    public String createLiquid(
            @ModelAttribute(LIQUID_DTO) @Valid LiquidDto liquidDto,
            BindingResult bindingResult,
            @RequestParam(value = STORE_ID, required = false) Long storeId,
            @RequestParam(value = PROVIDER_ID, required = false) Long providerId) {
        if (bindingResult.hasErrors()){return TO_LIQUID_CREATE;}

        liquidDto.setProviderId(providerId);
        liquidDto.setStoreId(storeId);
        liquidService.createLiquid(liquidDto);

        return REDIRECT_LIQUID;
    }

    @GetMapping(MAPPING_ID)
    public String update(@PathVariable(ID) Long id,
                         Model model) {
        model.addAttribute(LIQUID_DTO, liquidService.findLiquidById(id));
        model.addAttribute(PROVIDERS_LIST, providerService.findAllProviders());
        model.addAttribute(STORES_LIST, storeService.findAllStores());
        return TO_LIQUID_EDIT;
    }

    @PatchMapping(MAPPING_ID)
    public String updateLiquid(@ModelAttribute(LIQUID_DTO) LiquidDto liquidDto,
                               BindingResult bindingResult,
                               @RequestParam(value = STORE_ID, required = false) Long storeId,
                               @RequestParam(value = PROVIDER_ID, required = false) Long providerId) {
        if (bindingResult.hasErrors()){return TO_LIQUID_EDIT;}

        liquidDto.setProviderId(providerId);
        liquidDto.setStoreId(storeId);
        liquidService.updateLiquid(liquidDto);
        return REDIRECT_LIQUID;
    }

    @PostMapping(MAPPING_ID)
    public String delete(@PathVariable(ID) Long id)
    {
        liquidService.deleteLiquid(id);
        return  REDIRECT_LIQUID;
    }
}
