package vapeShop.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import vapeShop.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vapeShop.service.StoreService;

import java.util.List;

@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<StoreDto> storesDtoList = storeService.findAllStores();
        model.addAttribute("stores", storesDtoList);
        return "store/stores";
    }

    @GetMapping("/create")
    public String creationStore(Model model) {
        model.addAttribute("store", new StoreDto());
        return "store/add";
    }

    @PostMapping()
    public String createStore(@ModelAttribute("store") @Valid StoreDto storeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "store/add";

        storeService.createStore(storeDto);

        return "redirect:/store";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("store", storeService.findStoreById(id));
        return "store/edit";
    }

    @PatchMapping("/{id}")
    public String updateStore(@ModelAttribute("store") @Valid StoreDto storeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "store/edit";

        storeService.updateStore(storeDto);
        return "redirect:/store";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        storeService.deleteStore(id);
        return  "redirect:/store";
    }
}
