package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "_order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String location;
    private String phone;
    private String customerName;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> products;
    private boolean status;
    private BigDecimal orderAmount;

    public Order(String location, String phone, String customerName, List<Product> products,BigDecimal orderAmount) {
        this.location = location;
        this.phone = phone;
        this.customerName = customerName;
        this.products = products;
        this.orderAmount=orderAmount;
        this.status =true;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", customerName='" + customerName + '\'' +
                ", products=" + products +
                ", orderAmount=" + orderAmount +"$"+
                '}';
    }
}
