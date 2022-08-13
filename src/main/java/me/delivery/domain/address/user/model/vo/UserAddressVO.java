package me.delivery.domain.address.user.model.vo;

import lombok.Builder;
import lombok.Getter;
import me.delivery.domain.address.model.vo.AddressVO;
import me.delivery.domain.address.user.model.entity.UserAddress;

import java.time.LocalDateTime;


@Getter
public class UserAddressVO {
    private long id;
    private LocalDateTime createdAt;
    private String detailAddress;
    private String memo;
    private AddressVO address;

    @Builder
    private UserAddressVO(
        long id,
        LocalDateTime createdAt,
        String detailAddress,
        String memo,
        AddressVO address
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.detailAddress = detailAddress;
        this.memo = memo;
        this.address = address;
    }

    public static UserAddressVO from(UserAddress userAddress) {
        var address = AddressVO.from(userAddress.getAddress());
        return UserAddressVO.builder()
                .id(userAddress.getId())
                .createdAt(userAddress.getCreatedAt())
                .detailAddress(userAddress.getDetailAddress())
                .memo(userAddress.getMemo())
                .address(address)
                .build();
    }
}
