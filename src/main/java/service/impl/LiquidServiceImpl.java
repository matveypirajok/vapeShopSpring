package service.impl;

import dto.LiquidDto;
import lombok.RequiredArgsConstructor;
import mapper.impl.LiquidMapper;
import org.springframework.stereotype.Service;
import repositories.LiquidRep;
import service.LiquidService;

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
    public Optional<LiquidDto> findLiquidById(Long id) {
        return liquidRep.findById(id)
                .map(liquidMapper::mapToDTO);
    }
}
