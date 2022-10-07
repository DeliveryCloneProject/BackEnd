package me.delivery.domain.address.user.repository;

import me.delivery.domain.address.user.model.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findByUserIdAndDeletedAtIsNull(long userId);

}
