package com.BananesExport.OrderManager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private Long order_id;
    private Date delivery_date;
    private int quantity;
    private double price;
    private String  receiver_id;
}







