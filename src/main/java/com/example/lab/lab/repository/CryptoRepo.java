package com.example.lab.lab.repository;

import com.example.lab.lab.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepo extends JpaRepository<Crypto, Long> {
    Crypto findByName(String id);
    Crypto findByCryptoid(Long id);
    Crypto findBySymbol(String symbol);
}
