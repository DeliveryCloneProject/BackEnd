package me.delivery.domain.address.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.delivery.domain.address.model.vo.AddressVO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;
    private String metropolitan;
    private String city;
    private String town;
    private String road;
    private Integer mainNumber;
    private Integer subNumber;
    private BigDecimal x;
    private BigDecimal y;

    @Builder
    private Address(
        String address,
        String metropolitan,
        String city,
        String town,
        String road,
        Integer mainNumber,
        Integer subNumber,
        BigDecimal x,
        BigDecimal y
    ) {
        this.address = address;
        this.metropolitan = metropolitan;
        this.city = city;
        this.town = town;
        this.road = road;
        this.mainNumber = mainNumber;
        this.subNumber = subNumber;
        this.x = x;
        this.y = y;
    }

    public AddressVO toImmutable() {
        return AddressVO.builder()
                .id(id)
                .address(address)
                .metropolitan(metropolitan)
                .city(city)
                .town(town)
                .road(road)
                .mainNumber(mainNumber)
                .subNumber(subNumber)
                .x(x)
                .y(y)
                .build();
    }
}
