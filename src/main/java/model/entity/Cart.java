package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Entity(name = "cart")
@NoArgsConstructor
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int count;
    private BigDecimal totalAmount;
    @ManyToMany
    private List<Product> products;

    public Cart(List<Product> products) {
        this.count = products.size();
        this.totalAmount=amountSum(products);
        this.products = products;
    }
    public void addProduct(Product product){
        this.products.add(product);
    }
    public BigDecimal amountSum(List<Product> products){
        BigDecimal amount=new BigDecimal(0);
        for (int i = 0; i < products.size(); i++) {
           amount=amount.add(products.get(i).getAmount());
        }
        return amount;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        this.totalAmount = amountSum(products);
        this.count=products.size();
    }
}
