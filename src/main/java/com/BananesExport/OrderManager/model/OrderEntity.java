package com.BananesExport.OrderManager.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private Long order_id;
    @Column(nullable = false)
    private Date delivery_date;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false, precision=10, scale=2)
    private double price;
    @Column(nullable = false)
    private String  receiver_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderEntity order = (OrderEntity) o;
        return order_id != null && Objects.equals(order_id, order.order_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}







