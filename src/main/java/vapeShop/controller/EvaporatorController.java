package vapeShop.controller;

import jakarta.validation.Valid;
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

import static vapeShop.data.ControllerData.*;
import static vapeShop.data.EntityData.STORE_ID;
import static vapeShop.data.EntityData.PROVIDER_ID;

@Controller
@RequestMapping(MAPPING_EVAPORATOR)
@RequiredArgsConstructor
public class EvaporatorController {

    private final EvaporatorService evaporatorService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<EvaporatorDto> evaporatorDtoList = evaporatorService.findAllEvaporators();
        model.addAttribute(EVAPORATORS_LIST, evaporatorDtoList);
        return TO_EVAPORATORS;
    }

    @GetMapping(MAPPING_CREATE)
    public String creationEvaporator(Model model) {
        model.addAttribute(EVAPORATOR_DTO, new EvaporatorDto());
        model.addAttribute(PROVIDERS_LIST, providerService.findAllProviders());
        model.addAttribute(STORES_LIST, storeService.findAllStores());
        return TO_EVAPORATOR_CREATE;
    }

    @PostMapping()
    public String createEvaporator(
            @ModelAttribute(EVAPORATOR_DTO) @Valid EvaporatorDto evaporatorDto,
            BindingResult bindingResult,
            @RequestParam(value = STORE_ID, required = false) Long store_id,
            @RequestParam(value = PROVIDER_ID, required = false) Long provider_id) {
        if (bindingResult.hasErrors()){return TO_EVAPORATOR_CREATE;}

        evaporatorDto.setProviderId(provider_id);
        evaporatorDto.setStoreId(store_id);
        evaporatorService.createEvaporator(evaporatorDto);

        return REDIRECT_EVAPORATOR;
    }

    @GetMapping(MAPPING_EDIT)
    public String update(@PathVariable(ID) Long id,
                         Model model) {
        model.addAttribute(EVAPORATOR_DTO, evaporatorService.findEvaporatorById(id));
        model.addAttribute(PROVIDERS_LIST, providerService.findAllProviders());
        model.addAttribute(STORES_LIST, storeService.findAllStores());
        return TO_EVAPORATOR_EDIT;
    }

    @PatchMapping(MAPPING_ID)
    public String updateEvaporator(@ModelAttribute(EVAPORATOR_DTO) @Valid EvaporatorDto evaporatorDto,
                                   BindingResult bindingResult,
                                   @RequestParam(value = STORE_ID, required = false) Long store_id,
                                   @RequestParam(value = PROVIDER_ID, required = false) Long provider_id) {
        if (bindingResult.hasErrors()){return TO_EVAPORATOR_EDIT;}

        evaporatorDto.setProviderId(provider_id);
        evaporatorDto.setStoreId(store_id);
        evaporatorService.updateEvaporator(evaporatorDto);
        return REDIRECT_EVAPORATOR;
    }

    @PostMapping(MAPPING_ID)
    public String delete(@PathVariable(ID) Long id)
    {
        evaporatorService.deleteEvaporator(id);
        return  REDIRECT_EVAPORATOR;
    }
}
