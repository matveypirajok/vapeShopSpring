package service.impl;

import dto.EvaporatorDto;
import lombok.RequiredArgsConstructor;
import mapper.impl.EvaporatorMapper;
import org.springframework.stereotype.Service;
import repositories.EvaporatorRep;
import service.EvaporatorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaporatorServiceImpl implements EvaporatorService {
    private final EvaporatorMapper evaporatorMapper;
    private final EvaporatorRep evaporatorRep;

    @Override
    public void createEvaporator(EvaporatorDto evaporatorDto) {
        evaporatorRep.save(evaporatorMapper.mapToEntity(evaporatorDto));
    }

    @Override
    public List<EvaporatorDto> findAllEvaporators() {
        return evaporatorRep.findAll().stream().map(evaporatorMapper::mapToDTO).toList();
    }

    @Override
    public void updateEvaporator(EvaporatorDto evaporatorDto) {
        evaporatorRep.save(evaporatorMapper.mapToEntity(evaporatorDto));
    }

    @Override
    public boolean deleteEvaporator(Long id) {
        return evaporatorRep.findById(id)
                .map(entity -> {
                    evaporatorRep.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<EvaporatorDto> findEvaporatorById(Long id) {
        return evaporatorRep.findById(id)
                .map(evaporatorMapper::mapToDTO);
    }
}
