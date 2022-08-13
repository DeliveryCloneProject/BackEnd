package me.delivery.domain.address.model.vo;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AddressCreate {
    private String addressName;
    private String city;
    private String town;
    private BigDecimal x;
    private BigDecimal y;


    public static class LotAddress {

    }

    public static class RoadAddress {

    }
}


