package com.BananesExport.OrderManager.controller;

import com.BananesExport.OrderManager.model.OrderEntity;
import com.BananesExport.OrderManager.model.ReceiverEntity;
import com.BananesExport.OrderManager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping("/getAllOrders/{receiver_id}")
    public List<OrderEntity> getAllOrders(@PathVariable String receiver_id) {
        return service.getAllOrders(receiver_id);
    }

    @PostMapping("/addOrder")
    public ResponseEntity addOrder(@RequestBody OrderEntity orderEntity) {
        OrderEntity order;
        try {
            order = service.addOrder(orderEntity);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(order, HttpStatus.CREATED);
    }

    @PutMapping("/updateOrder")
    public ResponseEntity updateOrder(@RequestBody OrderEntity orderEntity) {
        OrderEntity order;
        try {
            order = service.updateOrder(orderEntity);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(order, HttpStatus.OK);
    }
    @GetMapping("/deleteOrder/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") Long id) {
        try {
            service.deleteOrder(id);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("Order deleted with Id : "+ id, HttpStatus.OK);
    }
}
