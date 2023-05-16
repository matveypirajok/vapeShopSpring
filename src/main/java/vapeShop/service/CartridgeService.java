package vapeShop.service;

import vapeShop.dto.CartridgeDto;

import java.util.List;

public interface CartridgeService {

    void createCartridge(CartridgeDto cartridgeDto);

    List<CartridgeDto> findAllCartridges();

    void updateCartridge(CartridgeDto cartridgeDto);

    boolean deleteCartridge(Long id);

    CartridgeDto findCartridgeById(Long id);
}
