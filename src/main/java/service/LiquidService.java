package service;

import dto.LiquidDto;

import java.util.List;
import java.util.Optional;

public interface LiquidService {

    void createLiquid(LiquidDto liquidDto);

    List<LiquidDto> findAllLiquids();

    void updateLiquid(LiquidDto liquidDto);

    boolean deleteLiquid(Long id);

    Optional<LiquidDto> findLiquidById(Long id);
}
