package model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "brand")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name="findAllBrand",query = "select b from brand b where b.status=true "),
        @NamedQuery(name="findByNameBrand",query = "select b from brand b where b.name = :name and b.status=true")
})
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private boolean status;
    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    public Brand(String name, String description) {
        this.name = name;
        this.description = description;
        setStatus(true);
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
