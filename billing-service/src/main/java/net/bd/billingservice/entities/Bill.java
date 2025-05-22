package net.bd.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.bd.billingservice.module.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity @Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private Long customerId;
    // c'est une relation bdirectionnelle
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems = new ArrayList<>();

    @Transient private Customer customer;

}
