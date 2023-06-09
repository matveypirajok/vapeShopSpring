package vapeShop.repositories;

import vapeShop.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRep extends JpaRepository<Device, Long> {
}
