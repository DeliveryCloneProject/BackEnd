package me.delivery.domain.address.user.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.delivery.config.exception.ForbiddenException;
import me.delivery.domain.address.model.entity.Address;
import me.delivery.domain.address.model.vo.AddressVO;
import me.delivery.domain.address.user.model.error_code.UserAddressErrorCode;
import me.delivery.domain.address.user.model.vo.UserAddressVO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserAddress {
    // id, createdAt - baseEntity를 상속후 삭제될 예정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CreatedDate
    private LocalDateTime createdAt;

    private long addressId;
    private long userId;
    private String detailAddress;
    private String memo;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "addressId", insertable = false, updatable = false)
    private Address address;

    @Builder
    private UserAddress(long addressId, long userId, String detailAddress, String memo) {
        this.addressId = addressId;
        this.detailAddress = detailAddress;
        this.memo = memo;
    }

    public UserAddressVO toImmutable(AddressVO addressVO) {
        return UserAddressVO.builder()
                .id(id)
                .createdAt(createdAt)
                .memo(memo)
                .address(addressVO)
                .build();
    }

    public void delete(long userId) {
        if (this.userId != userId) {
            throw new ForbiddenException(UserAddressErrorCode.DeleteForbidden);
        }

        deletedAt = LocalDateTime.now();
    }

    public void updateMemoAndDetailAddress(int userId, String memo, String detailAddress) {
        if (this.userId != userId) {
            throw new ForbiddenException(UserAddressErrorCode.UpdateForbidden);
        }

        this.memo = memo;
        this.detailAddress = detailAddress;
    }
}
