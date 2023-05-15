package repositories;

import entity.Cartridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartridgeRep extends JpaRepository<Cartridge, Long> {
}
