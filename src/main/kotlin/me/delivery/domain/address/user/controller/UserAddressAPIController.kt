package me.delivery.domain.address.user.controller;

import lombok.RequiredArgsConstructor;
import me.delivery.domain.address.user.model.vo.UserAddressSave;
import me.delivery.domain.address.user.model.vo.UserAddressUpdate;
import me.delivery.domain.address.user.model.vo.UserAddressVO;
import me.delivery.domain.address.user.service.UserAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address/users")
public class UserAddressAPIController {
    private final UserAddressService userAddressService;

    @GetMapping("/me")
    public List<UserAddressVO> myAddress() {
        var userId = 0; // user가 작업된 이후 변경될 예정
        return userAddressService.myAddresses(userId);
    }

    @PostMapping
    public UserAddressVO save(@RequestBody @Valid UserAddressSave param) {
        return userAddressService.save(param);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        var userId = 0; // user가 작업된 이후 변경될 예정
        userAddressService.delete(id, userId);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMemo(@PathVariable long id, @RequestBody @Valid UserAddressUpdate param) {
        var userId = 0; // user가 작업된 이후 변경될 예정
        userAddressService.updateMemo(id, userId, param.getMemo(), param.getDetailAddress());
    }
}
