package vapeShop.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vapeShop.dto.StoreDto;
import vapeShop.dto.UserDto;
import vapeShop.entity.Store;
import vapeShop.entity.users.Role;
import vapeShop.entity.users.User;
import vapeShop.mapper.Mapper;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public User mapToEntity(UserDto object) {
        return User.builder()
                .id(object.getId())
                .login(object.getLogin())
                .password(object.getPassword())
                .email(object.getEmail())
                .role(Role.valueOf(object.getRole()))
                .phone(object.getPhone())
                .build();
    }

    @Override
    public UserDto mapToDTO(User object) {
        return UserDto.builder()
                .id(object.getId())
                .login(object.getLogin())
                .password(object.getPassword())
                .email(object.getEmail())
                .role(object.getRole().toString())
                .phone(object.getPhone())
                .build();
    }
}
