package me.delivery.domain.address.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.delivery.domain.address.model.entity.Address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AddressSaveParam {
    @JsonProperty("address_name")
    @NotBlank(message = "주소가 입력되지 않았습니다.")
    private String address;
    @JsonProperty("region_1depth_name")
    @NotBlank(message = "광역시/도를 입력해주세요.")
    private String metropolitan;
    @JsonProperty("region_2depth_name")
    @NotBlank(message = "시/군/구를 입력해주세요.")
    private String city;
    @NotBlank(message = "읍면동을 입력해주세요.")
    @JsonProperty("region_3depth_name")
    private String town;
    @NotBlank(message = "도로명 주소만 입력 가능합니다.")
    @JsonProperty("road_name")
    private String road;
    @NotNull(message = "본번이 입력되지 않았습니다.")
    @Positive(message = "유요한 본번을 입력해주세요.")
    @JsonProperty("main_building_no")
    private Integer mainNumber;
    @NotNull(message = "부번이 입력되지 않았습니다.")
    @JsonProperty("sub_building_no")
    private Integer subNumber;
    @NotNull(message = "좌표가 없는 건물입니다.")
    private BigDecimal x;
    @NotNull(message = "좌표가 없는 건물입니다.")
    private BigDecimal y;

    public Address toEntity() {
        return Address.builder()
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
