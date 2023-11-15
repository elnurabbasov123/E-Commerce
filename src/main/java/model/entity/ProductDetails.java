package model.entity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Embeddable
public class ProductDetails {
    private String description;
    private String color;
    private double weight;

    public ProductDetails(String description, String color, double weight) {
        this.description = description;
        this.color = color;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
