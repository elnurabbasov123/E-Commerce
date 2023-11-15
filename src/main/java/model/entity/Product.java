package model.entity;

import jakarta.persistence.*;
import lombok.*;
import model.query.ProductQuery;

import java.math.BigDecimal;
import java.util.List;
@Entity(name = "products")
@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name="findAllProduct",query = ProductQuery.FIND_ALL),
        @NamedQuery(name="findByNameProduct",query = ProductQuery.FIND_BY_NAME),
        @NamedQuery(name="findByIdProduct",query = ProductQuery.FIND_BY_ID)
})
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
        private  long id;
        private String name;
        private BigDecimal amount;
        @ElementCollection(fetch = FetchType.LAZY)
        private List<String> comments;
        private int star;
        private int likes;
        private int remainCount;
        private int sales;
        private boolean status;
        private int totalStars;
        @Embedded
        private ProductDetails productDetails;
        @ManyToOne(fetch = FetchType.LAZY)
        private Category category;
        @ManyToOne(fetch = FetchType.LAZY)
        private Brand brand;


        public void totalStar(int news) {
                this.totalStars=this.totalStars+news;
        }

        public void setStar() {
                this.star = totalStars / sales;
        }

        @Override
        public String toString() {
                return "Product{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", amount=" + amount +"$"+
                        ", comments=" + comments +
                        ", star=" + star +
                        ", likes=" + likes +
                        ", remainCount=" + remainCount +
                        ", productDetails=" + productDetails +
                        '}';
        }
}
