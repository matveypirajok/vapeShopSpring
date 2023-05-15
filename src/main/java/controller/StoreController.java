package controller;

import dto.ProviderDto;
import dto.StoreDto;
import entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.ProviderService;
import service.StoreService;

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

    @PostMapping("/add")
    public String create(Model model) {
        model.addAttribute("store", new StoreDto());
        return "store/add";
    }

    @PostMapping("/create")
    public String createStore(@ModelAttribute("store") StoreDto storeDto) {
        storeService.createStore(storeDto);

        return "redirect:/store";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("store", storeService.findStoreById(id));
        return "store/edit";
    }

    @PatchMapping("/{id}")
    public String updateStore(@ModelAttribute("store") StoreDto storeDto) {
        storeService.updateStore(storeDto);
        return "redirect:/store";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        storeService.deleteStore(id);
        return  "redirect:/store";
    }
}
