package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(name = "customers")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name="findAllCustomer",query = "select c from customers c where c.status=true"),
        @NamedQuery(name="findByNameCustomer",query = "select c from customers c where c.name = :name and c.status=true")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birthDate")
    private LocalDate birthDate;
    @Column(name = "fin",unique = true)
    private String fin;
    @Column(name = "account")
    private BigDecimal account;
    @Column(name = "login",unique = true)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "adress")
    private String adress;
    @Builder.Default
    private boolean status=true;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Cart cart;

    public Customer(String name, String surname, String birthDate, String fin, BigDecimal account, String login, String password, String adress) {
        this.name = name;
        this.surname = surname;
        setBirthDate(birthDate);
        setFin(fin);
        this.account = account;
        this.login = login;
        this.password = password;
        this.adress = adress;
        this.status=true;
    }

    public void setBirthDate(String str) {
        String[] split = str.split("/");
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);
        LocalDate localDate = LocalDate.of(year, month, day);
        this.birthDate = localDate;
    }

    public void setFin(String fin) {
        this.fin = fin.toUpperCase();
    }

    public void increaseAmount(BigDecimal money) {
        this.account = this.account.add(money);
    }

    public void reduceAmount(BigDecimal money) {
        this.account = this.account.subtract(money);
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
