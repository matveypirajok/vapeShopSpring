package vapeShop.repositories;

import vapeShop.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRep extends JpaRepository<Accessory, Long> {
}
