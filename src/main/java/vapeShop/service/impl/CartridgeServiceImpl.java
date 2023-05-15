package vapeShop.service.impl;

import vapeShop.dto.CartridgeDto;
import lombok.RequiredArgsConstructor;
import vapeShop.entity.Accessory;
import vapeShop.entity.Cartridge;
import vapeShop.mapper.impl.CartridgeMapper;
import org.springframework.stereotype.Service;
import vapeShop.repositories.CartridgeRep;
import vapeShop.service.CartridgeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartridgeServiceImpl implements CartridgeService {

    private final CartridgeRep cartridgeRep;
    private final CartridgeMapper cartridgeMapper;

    @Override
    public void createCartridge(CartridgeDto cartridgeDto) {

        cartridgeRep.save(cartridgeMapper.mapToEntity(cartridgeDto));
    }

    @Override
    public List<CartridgeDto> findAllCartridges() {

        return cartridgeRep.findAll().stream().map(cartridgeMapper::mapToDTO).toList();
    }

    @Override
    public void updateCartridge(CartridgeDto cartridgeDto) {
        cartridgeRep.save(cartridgeMapper.mapToEntity(cartridgeDto));
    }

    @Override
    public boolean deleteCartridge(Long id) {
        return cartridgeRep.findById(id)
                .map(entity -> {
                    cartridgeRep.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public CartridgeDto findCartridgeById(Long id) {
        Optional<Cartridge> cartridge = cartridgeRep.findById(id);

        return cartridgeMapper.mapToDTO(cartridge.orElseThrow());
    }
}
