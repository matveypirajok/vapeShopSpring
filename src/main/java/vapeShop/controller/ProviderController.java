package vapeShop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import vapeShop.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vapeShop.service.ProviderService;

import java.util.List;

@Controller
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping
    public String findAll(Model model) {
        List<ProviderDto> providersDtoList = providerService.findAllProviders();
        model.addAttribute("providers", providersDtoList);
        return "provider/providers";
    }

    @GetMapping("/create")
    public String creationProvider(Model model) {
        model.addAttribute("provider", new ProviderDto());
        return "provider/add";
    }

    @PostMapping()
    public String createProvider(@ModelAttribute("provider") @Valid ProviderDto providerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "provider/add";

        providerService.createProvider(providerDto);
        return "redirect:/provider";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("provider", providerService.findProviderById(id));
        return "provider/edit";
    }

    @PatchMapping("/{id}")
    public String updateProvider(@ModelAttribute("provider") @Valid ProviderDto providerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "provider/edit";

       providerService.updateProvider(providerDto);
        return "redirect:/provider";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        providerService.deleteProvider(id);
        return  "redirect:/provider";
    }
}
