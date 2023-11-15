package model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "admin")
@Getter
@NamedQueries({
        @NamedQuery(name="findAllAdmin",query = "select a from admin a")
})
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String login;
    private String password;

    public Admin(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
}
