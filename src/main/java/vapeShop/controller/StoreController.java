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

import static vapeShop.data.ControllerData.*;

@Controller
@RequestMapping(MAPPING_STORE)
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public String findAll(Model model) {
        List<StoreDto> storesDtoList = storeService.findAllStores();
        model.addAttribute(STORES_LIST, storesDtoList);
        return TO_STORES;
    }

    @GetMapping(MAPPING_CREATE)
    public String creationStore(Model model) {
        model.addAttribute(STORE_DTO, new StoreDto());
        return TO_STORE_CREATE;
    }

    @PostMapping()
    public String createStore(@ModelAttribute(STORE_DTO) @Valid StoreDto storeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return TO_STORE_CREATE;

        storeService.createStore(storeDto);

        return REDIRECT_STORE;
    }

    @GetMapping(MAPPING_EDIT)
    public String update(@PathVariable(ID) Long id,
                         Model model) {
        model.addAttribute(STORE_DTO, storeService.findStoreById(id));
        return TO_STORE_EDIT;
    }

    @PatchMapping(MAPPING_ID)
    public String updateStore(@ModelAttribute(STORE_DTO) @Valid StoreDto storeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return TO_STORE_EDIT;

        storeService.updateStore(storeDto);
        return REDIRECT_STORE;
    }

    @PostMapping(MAPPING_ID)
    public String delete(@PathVariable(ID) Long id)
    {
        storeService.deleteStore(id);
        return  REDIRECT_STORE;
    }
}
