package me.delivery.domain.address.model.vo

import com.fasterxml.jackson.annotation.JsonProperty
import me.delivery.domain.address.model.entity.Address
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class AddressSaveParam(
    @JsonProperty("address_name")
    @NotBlank(message = "주소가 입력되지 않았습니다.")
    val address: String,

    @JsonProperty("region_1depth_name")
    @NotBlank(message = "광역시/도를 입력해주세요.")
    val metropolitan: String,

    @JsonProperty("region_2depth_name")
    @NotBlank(message = "시/군/구를 입력해주세요.")
    val city: String,

    @JsonProperty("region_3depth_name")
    @NotBlank(message = "읍면동을 입력해주세요.")
    val town: String,

    @JsonProperty("road_name")
    @NotBlank(message = "도로명 주소만 입력 가능합니다.")
    val road: String,

    @JsonProperty("main_building_no")
    @NotNull(message = "본번이 입력되지 않았습니다.")
    @Positive(message = "유요한 본번을 입력해주세요.")
    val mainNumber: Int,

    @JsonProperty("sub_building_no")
    @NotNull(message = "부번이 입력되지 않았습니다.")
    val subNumber: Int,

    @NotNull(message = "좌표가 없는 건물입니다.")
    val x: BigDecimal,

    @NotNull(message = "좌표가 없는 건물입니다.")
    val y: BigDecimal,

) {
    fun toEntity(): Address {
        return Address(
            address = address,
            metropolitan = metropolitan,
            city = city,
            town = town,
            road = road,
            mainNumber = mainNumber,
            subNumber = subNumber,
            x = x,
            y = y
        )
    }
}