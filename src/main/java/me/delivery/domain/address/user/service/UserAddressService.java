package me.delivery.domain.address.user.service;

import lombok.RequiredArgsConstructor;
import me.delivery.config.exception.NotFoundException;
import me.delivery.domain.address.service.AddressService;
import me.delivery.domain.address.user.model.error_code.UserAddressErrorCode;
import me.delivery.domain.address.user.model.vo.UserAddressSave;
import me.delivery.domain.address.user.model.vo.UserAddressVO;
import me.delivery.domain.address.user.repository.UserAddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAddressService {
    private final UserAddressRepository userAddressRepository;
    private final AddressService addressService;

    public List<UserAddressVO> myAddresses(long userId) {
        return userAddressRepository.findByUserIdAndDeletedAtIsNull(userId)
                .stream()
                .map(UserAddressVO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserAddressVO save(UserAddressSave param) {
        var address = param.getAddress().toEntity();

        var addressVO = addressService.save(address);

        var userAddress = param.toUserAddress(address.getId());

        userAddressRepository.save(userAddress);

        return userAddress.toImmutable(addressVO);
    }

    @Transactional
    public void delete(long id, long userId) {
        var userAddress = userAddressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserAddressErrorCode.NotFound));

        userAddress.delete(userId);
    }

    @Transactional
    public void updateMemo(long id, int userId, String memo, String detailAddress) {
        var userAddress = userAddressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserAddressErrorCode.NotFound));

        userAddress.updateMemoAndDetailAddress(userId, memo, detailAddress);
    }
}
