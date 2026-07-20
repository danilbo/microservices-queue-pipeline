package com.web.storage.repository;

import com.web.storage.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {


}
