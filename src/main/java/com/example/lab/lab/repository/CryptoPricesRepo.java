package com.example.lab.lab.repository;

import com.example.lab.lab.entity.CryptoUserPrice;
import com.example.lab.lab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoPricesRepo extends JpaRepository<CryptoUserPrice, Long> {
    Boolean existBySymbolAndUsers(String symbol, User user);
}
