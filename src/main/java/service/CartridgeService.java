package service;

import dto.CartridgeDto;

import java.util.List;
import java.util.Optional;

public interface CartridgeService {

    void createCartridge(CartridgeDto cartridgeDto);

    List<CartridgeDto> findAllCartridges();

    void updateCartridge(CartridgeDto cartridgeDto);

    boolean deleteCartridge(Long id);

    Optional<CartridgeDto> findCartridgeById(Long id);
}
