package com.BananesExport.OrderManager.service;

import com.BananesExport.OrderManager.commun.Constants;
import com.BananesExport.OrderManager.model.OrderEntity;
import com.BananesExport.OrderManager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders(String receiver_id) {
        return orderRepository.findByReceiver_id(receiver_id);
    }
    public OrderEntity addOrder(OrderEntity orderEntity) throws Exception {
        if (orderEntity.getOrder_id() != null){
            Optional<OrderEntity> orderEntityDBOpt = orderRepository.findById(orderEntity.getOrder_id());
            if (orderEntityDBOpt.isPresent()) {
                throw new EntityExistsException("Order already existe");
            }
        }
        LocalDateTime todayDate = LocalDateTime.now();
        Date date = new Date(System.currentTimeMillis()+ (1000 * 60 * 60 * 24*7));
        System.out.println(date);
        if (orderEntity.getDelivery_date().before(date)){
            throw new DateTimeException("Delivery date is less than 1 week");
        }
        if (orderEntity.getQuantity() < 0|| orderEntity.getQuantity() > Constants.MAX_BANANAS_QUANTITY ||
                orderEntity.getQuantity() % 25!= 0) {
            throw new Exception(Constants.INVALIDE_BANANAS_QUANTITY_MESSAGE);
        }
        orderEntity.setPrice(orderEntity.getQuantity() * Constants.BANANAS_PRICE_KG);

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
