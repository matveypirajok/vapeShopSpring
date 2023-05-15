package vapeShop.service;

import vapeShop.dto.StoreDto;
import vapeShop.dto.UserDto;

import java.util.List;

public interface UserService {

    void createUser(UserDto userDto);

    List<UserDto> findAllUsers();

    void updateUser(UserDto userDto);

    boolean deleteUser(Long id);

    UserDto findUserById(Long id);
}
