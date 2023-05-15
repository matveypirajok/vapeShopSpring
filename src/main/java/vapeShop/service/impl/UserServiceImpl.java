package vapeShop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vapeShop.dto.StoreDto;
import vapeShop.dto.UserDto;
import vapeShop.entity.Store;
import vapeShop.entity.users.User;
import vapeShop.mapper.impl.UserMapper;
import vapeShop.repositories.UserRep;
import vapeShop.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final UserRep userRep;

    @Override
    public void createUser(UserDto userDto) {
        userRep.save(userMapper.mapToEntity(userDto));
    }

    @Override
    public List<UserDto> findAllUsers() {
       return userRep.findAll().stream().map(userMapper::mapToDTO).toList();
    }

    @Override
    public void updateUser(UserDto userDto) {
        userRep.save(userMapper.mapToEntity(userDto));
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRep.findById(id)
                .map(entity -> {
                    userRep.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> user = userRep.findById(id);

        return userMapper.mapToDTO(user.orElseThrow());
    }
}
