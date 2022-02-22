package com.example.lab.lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Crypto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cryptoid;
    private String symbol;
    private String name;
    private String nameid;
    private Integer rank;
    private Double price_usd;
    private Double price_btc;
    @ManyToMany(mappedBy = "crypto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> userSet;

    public Crypto(Long crypto_id, String symbol, String name, String nameid, Integer rank, Double price_usd, Double price_btc) {
        this.cryptoid = crypto_id;
        this.symbol = symbol;
        this.name = name;
        this.nameid = nameid;
        this.rank = rank;
        this.price_usd = price_usd;
        this.price_btc = price_btc;
    }
}
