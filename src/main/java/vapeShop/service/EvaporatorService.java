package vapeShop.service;

import vapeShop.dto.EvaporatorDto;

import java.util.List;

public interface EvaporatorService {

    void createEvaporator(EvaporatorDto evaporatorDto);

    List<EvaporatorDto> findAllEvaporators();

    void updateEvaporator(EvaporatorDto evaporatorDto);

    boolean deleteEvaporator(Long id);

    EvaporatorDto findEvaporatorById(Long id);
}
