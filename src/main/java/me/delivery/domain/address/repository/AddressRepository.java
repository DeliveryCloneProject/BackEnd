package me.delivery.domain.address.repository;

import me.delivery.domain.address.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByAddress(String address);
}
