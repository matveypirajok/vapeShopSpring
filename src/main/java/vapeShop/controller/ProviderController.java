package vapeShop.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import vapeShop.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vapeShop.service.ProviderService;

import java.util.List;

import static vapeShop.data.ControllerData.*;

@Controller
@RequestMapping(MAPPING_PROVIDER)
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping
    public String findAll(Model model) {
        List<ProviderDto> providersDtoList = providerService.findAllProviders();
        model.addAttribute(PROVIDERS_LIST, providersDtoList);
        return TO_PROVIDERS;
    }

    @GetMapping(MAPPING_CREATE)
    public String creationProvider(Model model) {
        model.addAttribute(PROVIDER_DTO, new ProviderDto());
        return TO_PROVIDER_CREATE;
    }

    @PostMapping()
    public String createProvider(@ModelAttribute(PROVIDER_DTO) @Valid ProviderDto providerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return TO_PROVIDER_CREATE;

        providerService.createProvider(providerDto);
        return REDIRECT_PROVIDER;
    }

    @GetMapping(MAPPING_EDIT)
    public String update(@PathVariable(ID) Long id,
                         Model model) {
        model.addAttribute(PROVIDER_DTO, providerService.findProviderById(id));
        return TO_PROVIDER_EDIT;
    }

    @PatchMapping(MAPPING_ID)
    public String updateProvider(@ModelAttribute(PROVIDER_DTO) @Valid ProviderDto providerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return TO_PROVIDER_EDIT;

       providerService.updateProvider(providerDto);
        return REDIRECT_PROVIDER;
    }

    @PostMapping(MAPPING_ID)
    public String delete(@PathVariable(ID) Long id)
    {
        providerService.deleteProvider(id);
        return  REDIRECT_PROVIDER;
    }
}
