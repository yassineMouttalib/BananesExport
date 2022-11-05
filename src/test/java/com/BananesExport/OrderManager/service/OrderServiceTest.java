package com.BananesExport.OrderManager.service;

import com.BananesExport.OrderManager.commun.Constants;
import com.BananesExport.OrderManager.model.OrderEntity;
import com.BananesExport.OrderManager.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    OrderService orderService = new OrderService();
    List<OrderEntity> orderEntities;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    @BeforeEach
    void setUp() throws ParseException {
        String dateInString = "15-11-2022";
        Date date = formatter.parse(dateInString);
        OrderEntity orderEntity1 = new OrderEntity(1L,date, 100, 2.5, "1");
        OrderEntity orderEntity2 = new OrderEntity(2L,date, 150, 2.5, "1");
        OrderEntity orderEntity3 = new OrderEntity(3L,date, 200, 2.5, "2");

        orderEntities = Arrays.asList(orderEntity1, orderEntity2, orderEntity3);
    }

    @Test
    void getALlOrders() {
        when(orderRepository.findByReceiver_id("1")).thenReturn(Arrays.asList(orderEntities.get(0),orderEntities.get(0)));
        int ordersNum =orderService.getAllOrders("1").size();
        assertEquals(2, ordersNum);
    }

    @Test
    void addOrderTest() throws Exception {
        OrderEntity orderEntity = orderEntities.get(0);
        System.out.println(orderEntity);
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        orderService.addOrder(orderEntity);
        verify(orderRepository, times(1)).save(orderEntity);
    }
    @Test
    void invalideDeliveryDateAddOrderTest() throws Exception {
        OrderEntity orderEntity = orderEntities.get(2);
        String dateInString = "1-11-2022";
        Date date = formatter.parse(dateInString);
        orderEntity.setDelivery_date(date);
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        System.out.println(orderEntity);
        Assertions.assertThrows(DateTimeException.class, () ->
            orderService.addOrder(orderEntity)
        );
    }

    @Test
    void invalideQuantityAddOrderTest() {
        OrderEntity orderEntity = orderEntities.get(2);
        String dateInString = "1-11-2022";
        orderEntity.setQuantity(31);
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        System.out.println(orderEntity);
        Exception thrown = Assertions.assertThrows(Exception.class, () ->
            orderService.addOrder(orderEntity)
        );
        assertEquals(Constants.INVALIDE_BANANAS_QUANTITY_MESSAGE, thrown.getMessage());
    }

    @Test
    void checkPriceAddOrder() throws Exception {
        OrderEntity orderEntity = orderEntities.get(0);
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        OrderEntity order = orderService.addOrder(orderEntity);
        assertEquals(Constants.BANANAS_PRICE_KG * order.getQuantity(), order.getPrice());
    }
}