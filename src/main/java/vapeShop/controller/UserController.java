package vapeShop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vapeShop.dto.StoreDto;
import vapeShop.dto.UserDto;
import vapeShop.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public String findAll(Model model) {
        List<UserDto> storesDtoList = userService.findAllUsers();
        model.addAttribute("Users", storesDtoList);
        return "user/users";
    }

    @GetMapping("/create")
    public String creationUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/add";
    }

    @PostMapping()
    public String createStore(@ModelAttribute("user") UserDto userDto) {
        userService.createUser(userDto);

        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String updateStore(@ModelAttribute("store") UserDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/user";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        userService.deleteUser(id);
        return  "redirect:/user";
    }
}
