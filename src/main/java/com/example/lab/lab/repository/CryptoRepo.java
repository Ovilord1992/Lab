package com.example.lab.lab.repository;

import com.example.lab.lab.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoRepo extends JpaRepository<Crypto, Long> {
    Crypto findByName(String name);
    Crypto findByCryptoid(Long id);
    Crypto findBySymbol(String symbol);

    Boolean existsByName(String name);
    Boolean existsBySymbol(String symbol);
}
