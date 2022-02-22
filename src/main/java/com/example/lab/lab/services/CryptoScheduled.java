package com.example.lab.lab.services;
import com.example.lab.lab.dto.CryptoDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@EnableAsync
@Service
public class CryptoScheduled {
    List<String> cryptoList = List.of(
            "https://api.coinlore.net/api/ticker/?id=90",
            "https://api.coinlore.net/api/ticker/?id=80",
            "https://api.coinlore.net/api/ticker/?id=48543"
    );

    final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private CryptoService cryptoService;

    @Async
    @Scheduled(fixedRate = 1000 * 60)
    void createSchedule() {
        cryptoList.forEach(v -> cryptoService.saveCrypto(getGsonCrypto(v)));
    }

    private CryptoDto getGsonCrypto(String crypto){
        List response = restTemplate.getForEntity(crypto, List.class).getBody();
        Gson gson = new Gson();
        String s = gson.toJson(response.get(0));
        return gson.fromJson(s, CryptoDto.class);
    }


}
