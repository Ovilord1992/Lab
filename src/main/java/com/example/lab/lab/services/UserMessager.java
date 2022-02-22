package com.example.lab.lab.services;

import com.example.lab.lab.entity.Crypto;
import com.example.lab.lab.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableAsync
@Service
public class UserMessager {

    Logger logger = LoggerFactory.getLogger(UserMessager.class);

    @Async
    public void notify(Crypto crypto) {
        List<User> users = crypto.getUserSet();
        users
                .forEach(u -> u.getCryptoUserPrices().stream()
                .filter(c -> c.getSymbol().equals(crypto.getSymbol())).forEach(b -> {
                    double percent = (crypto.getPrice_usd() / b.getPrice()) * 100 - 100;
                    if (crypto.getPrice_usd() > (b.getPrice() + (b.getPrice() / 100)) ||
                    (crypto.getPrice_usd() + crypto.getPrice_usd() / 100) < b.getPrice()) {
                logger.warn(u.getUserName() + ":" + " " + crypto.getCryptoid() + " " + crypto.getSymbol() + " " + percent);
            }
        }));
    }

}
