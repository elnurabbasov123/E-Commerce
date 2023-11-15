package model.entity;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.*;

import java.util.List;

@Entity(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name="findAllCategory",query = "select c from categories c where c.status=true "),
        @NamedQuery(name="findByNameCategory",query = "select c from categories c where c.name =: name and status=true")
})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private boolean status;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

}
