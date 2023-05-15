package controller;

import dto.LiquidDto;
import dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.LiquidService;
import service.ProviderService;

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

    @PostMapping("/add")
    public String create(Model model) {
        model.addAttribute("provider", new ProviderDto());
        return "provider/add";
    }

    @PostMapping("/create")
    public String createProvider(@ModelAttribute("provider") ProviderDto providerDto) {
        providerService.createProvider(providerDto);

        return "redirect:/provider";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("provider", providerService.findProviderById(id));
        return "provider/edit";
    }

    @PatchMapping("/{id}")
    public String updateProvider(@ModelAttribute("provider") ProviderDto providerDto) {
       providerService.updateProvider(providerDto);
        return "redirect:/provider";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        providerService.deleteProvider(id);
        return  "redirect:/provider";
    }
}
