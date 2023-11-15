package model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "company")
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private BigDecimal totalAmount;
    private boolean status;

    public void increaseAmount(BigDecimal totalAmount) {
        this.totalAmount = this.totalAmount.add(totalAmount);
    }
}
