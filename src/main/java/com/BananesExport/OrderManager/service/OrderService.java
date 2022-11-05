package com.BananesExport.OrderManager.service;

import com.BananesExport.OrderManager.model.OrderEntity;
import com.BananesExport.OrderManager.model.ReceiverEntity;
import com.BananesExport.OrderManager.repository.OrderRepository;
import com.BananesExport.OrderManager.repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders(String receiver_id) {
       List<OrderEntity> orderEntityEntities =orderRepository.findByReceiver_id(receiver_id);
        return orderEntityEntities;
    }
    public OrderEntity addOrder(OrderEntity orderEntity) {
        if (orderEntity.getOrder_id() != null){
            Optional<OrderEntity> orderEntityDBOpt = orderRepository.findById(orderEntity.getOrder_id());
            if (orderEntityDBOpt.isPresent()) {
                throw new EntityExistsException("Order already existe");
            }
        }

        return orderRepository.save(orderEntity);
    }
    public OrderEntity updateOrder(OrderEntity orderEntity) {
        Optional<OrderEntity> orderEntityDBOpt = orderRepository.findById(orderEntity.getOrder_id());
        if (orderEntityDBOpt.isEmpty()) {
            return orderRepository.save(orderEntity);
        } else {
            OrderEntity orderEntityDB = orderEntityDBOpt.get();
            if (orderEntityDB.getReceiver_id() != null) orderEntityDB.setReceiver_id(orderEntity.getReceiver_id());
            if (orderEntityDB.getDelivery_date() != null) orderEntityDB.setDelivery_date(orderEntity.getDelivery_date());
            if (orderEntityDB.getQuantity() != 0) orderEntityDB.setQuantity(orderEntity.getQuantity());
            if (orderEntityDB.getPrice() != 0) orderEntityDB.setPrice(orderEntity.getPrice());

            return orderRepository.save(orderEntityDB);
        }
    }
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
