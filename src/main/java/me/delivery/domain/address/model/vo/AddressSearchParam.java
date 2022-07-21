package me.delivery.domain.address.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.delivery.domain.address.model.entity.AddressType;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressSearchParam {
    private String address;
    private AddressType type;
}
