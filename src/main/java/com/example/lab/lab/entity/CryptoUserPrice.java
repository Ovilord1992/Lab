package com.example.lab.lab.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "crypto_price_user")
public class CryptoUserPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "cryptoUserPrices", cascade = CascadeType.ALL)
    private Set<User> users;
    private Double price;
    private String symbol;
}
