package com.example.lab.lab.services;
import com.example.lab.lab.entity.Crypto;
import com.example.lab.lab.entity.CryptoUserPrice;
import com.example.lab.lab.entity.User;
import com.example.lab.lab.repository.CryptoRepo;
import com.example.lab.lab.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServices {

    final private UserRepo userRepo;
    final private CryptoRepo cryptoRepo;

    public void userRegistryOnSymbol(String name, String symbol){
        if (!cryptoRepo.existsBySymbol(symbol)){
            throw new RuntimeException("Error: symbol is exist");
        }
        Crypto crypto = cryptoRepo.findBySymbol(symbol);
        if (userRepo.existsByUserName(name)){
            User user = userRepo.findByUserName(name);
            Set<Crypto> cryptos = user.getCrypto();
                cryptos.add(crypto);
                user.setCrypto(cryptos);
            CryptoUserPrice cryptoUserPrice = new CryptoUserPrice();
                cryptoUserPrice.setPrice(crypto.getPrice_usd());
                cryptoUserPrice.setSymbol(crypto.getSymbol());
                cryptoUserPrice.setUser(user);
            Set<CryptoUserPrice> cryptoUserPrices = user.getCryptoUserPrices();
                cryptoUserPrices.add(cryptoUserPrice);
            userRepo.save(user);
        } else {
            User user = new User();
            Set<Crypto> cryptos = new HashSet<>();
                cryptos.add(crypto);
                user.setCrypto(cryptos);
                user.setUserName(name);
            CryptoUserPrice cryptoUserPrice = new CryptoUserPrice();
            Set<CryptoUserPrice> cryptoUserPrices = new HashSet<>();
                cryptoUserPrice.setPrice(crypto.getPrice_usd());
                cryptoUserPrice.setSymbol(crypto.getSymbol());
                cryptoUserPrice.setUser(user);
                cryptoUserPrices.add(cryptoUserPrice);
                user.setCryptoUserPrices(cryptoUserPrices);
            userRepo.save(user);
        }
    }
}
