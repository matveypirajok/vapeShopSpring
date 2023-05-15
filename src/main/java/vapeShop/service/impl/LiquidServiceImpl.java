package vapeShop.service.impl;

import vapeShop.dto.LiquidDto;
import lombok.RequiredArgsConstructor;
import vapeShop.entity.Accessory;
import vapeShop.entity.Liquid;
import vapeShop.mapper.impl.LiquidMapper;
import org.springframework.stereotype.Service;
import vapeShop.repositories.LiquidRep;
import vapeShop.service.LiquidService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LiquidServiceImpl implements LiquidService {
    private final LiquidMapper liquidMapper;
    private final LiquidRep liquidRep;

    @Override
    public void createLiquid(LiquidDto liquidDto) {
        liquidRep.save(liquidMapper.mapToEntity(liquidDto));
    }

    @Override
    public List<LiquidDto> findAllLiquids() {
        return liquidRep.findAll().stream().map(liquidMapper::mapToDTO).toList();
    }

    @Override
    public void updateLiquid(LiquidDto liquidDto) {
        liquidRep.save(liquidMapper.mapToEntity(liquidDto));
    }

    @Override
    public boolean deleteLiquid(Long id) {
        return liquidRep.findById(id)
                .map(entity -> {
                    liquidRep.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public LiquidDto findLiquidById(Long id) {
        Optional<Liquid> liquid = liquidRep.findById(id);

        return liquidMapper.mapToDTO(liquid.orElseThrow());
    }
}
