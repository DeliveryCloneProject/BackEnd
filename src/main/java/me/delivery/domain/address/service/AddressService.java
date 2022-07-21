package me.delivery.domain.address.service;

import lombok.RequiredArgsConstructor;
import me.delivery.domain.address.model.entity.Address;
import me.delivery.domain.address.model.vo.AddressVO;
import me.delivery.domain.address.repository.AddressQueryRepository;
import me.delivery.domain.address.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressQueryRepository addressQueryRepository;

    @Transactional
    public AddressVO save(Address address) {
        var find = findByAddress(address);

        if (Objects.nonNull(find)) {
            return address.toImmutable();
        }

        return addressRepository.save(address).toImmutable();
    }

    public Address findByAddress(Address address) {
        return addressRepository.findByAddress(address.getAddress());
    }
}
