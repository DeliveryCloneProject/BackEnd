package me.delivery.domain.address.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.delivery.domain.address.model.entity.Address;
import org.springframework.data.geo.Point;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressVO {
    private long id;
    private String address;
    private String metropolitan;
    private String city;
    private String town;
    private String road;
    private Integer mainNumber;
    private Integer subNumber;
    private Point point;

    public static AddressVO from(Address address) {
        return AddressVO.builder()
                .id(address.getId())
                .address(address.getAddress())
                .metropolitan(address.getMetropolitan())
                .city(address.getCity())
                .town(address.getTown())
                .road(address.getRoad())
                .mainNumber(address.getMainNumber())
                .subNumber(address.getSubNumber())
                .point(address.getPoint())
                .build();
    }
}
