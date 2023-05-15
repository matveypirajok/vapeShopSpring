package vapeShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vapeShop.entity.users.User;

@Repository
public interface UserRep extends JpaRepository<User, Long> {
}
