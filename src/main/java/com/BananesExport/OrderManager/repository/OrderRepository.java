package com.BananesExport.OrderManager.repository;

import com.BananesExport.OrderManager.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    @Query("select o from OrderEntity o where receiver_id = ?1")
    List<OrderEntity> findByReceiver_id(String receiver_id);
}
