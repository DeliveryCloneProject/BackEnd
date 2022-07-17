package me.delivery.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import me.delivery.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IUserService {
    private final UserRepository userRepository;
}
