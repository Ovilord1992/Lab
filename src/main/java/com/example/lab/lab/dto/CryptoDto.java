package com.example.lab.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoDto {
    private Long id;
    private String symbol;
    private String name;
    private String nameid;
    private Integer rank;
    private Double price_usd;
    private Double price_btc;

}
