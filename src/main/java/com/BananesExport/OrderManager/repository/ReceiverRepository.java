package com.BananesExport.OrderManager.repository;

import com.BananesExport.OrderManager.model.ReceiverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends JpaRepository<ReceiverEntity, Long> {
}
