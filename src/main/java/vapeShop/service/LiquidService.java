package vapeShop.service;

import vapeShop.dto.LiquidDto;

import java.util.List;

public interface LiquidService {

    void createLiquid(LiquidDto liquidDto);

    List<LiquidDto> findAllLiquids();

    void updateLiquid(LiquidDto liquidDto);

    boolean deleteLiquid(Long id);

    LiquidDto findLiquidById(Long id);
}
