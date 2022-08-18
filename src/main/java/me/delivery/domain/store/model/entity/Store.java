package me.delivery.domain.store.model.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import me.delivery.domain.user.model.entity.BaseEntity;

@Entity
@Getter
@Setter
public class Store extends BaseEntity {
    private String name;
    private StoreStatus status;
}
