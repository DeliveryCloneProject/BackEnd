package me.delivery.domain.login.repository;

import me.delivery.domain.login.model.dto.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRedisRepository extends CrudRepository<Login, Long> {
}
