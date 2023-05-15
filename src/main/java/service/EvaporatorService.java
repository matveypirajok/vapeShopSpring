package service;

import dto.EvaporatorDto;

import java.util.List;
import java.util.Optional;

public interface EvaporatorService {

    void createEvaporator(EvaporatorDto evaporatorDto);

    List<EvaporatorDto> findAllEvaporators();

    void updateEvaporator(EvaporatorDto evaporatorDto);

    boolean deleteEvaporator(Long id);

    Optional<EvaporatorDto> findEvaporatorById(Long id);
}
