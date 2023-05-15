package vapeShop.repositories;

import vapeShop.entity.Liquid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquidRep extends JpaRepository<Liquid, Long> {
}
