package me.delivery.domain.address.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.delivery.domain.address.model.vo.AddressVO;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "address")
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
    @Column(name = "point", columnDefinition = "POINT")
    private Point point;

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
        this.point = new Point(x.doubleValue(), y.doubleValue());
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
                .point(point)
                .build();
    }
}
