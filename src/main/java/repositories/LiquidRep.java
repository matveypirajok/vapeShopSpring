package repositories;

import entity.Liquid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquidRep extends JpaRepository<Liquid, Long> {
}
