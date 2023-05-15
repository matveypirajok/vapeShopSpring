package vapeShop.repositories;

import vapeShop.entity.Evaporator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaporatorRep extends JpaRepository<Evaporator, Long> {
}
