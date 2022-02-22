package com.example.lab.lab.services;
import com.example.lab.lab.dto.CryptoDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CryptoScheduled {

    final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CryptoService cryptoService;


    @Scheduled(fixedRate = 1000 * 60)
    void createSchedule(){
        List<String> cryptoList = List.of(
                "https://api.coinlore.net/api/ticker/?id=90",
                "https://api.coinlore.net/api/ticker/?id=80",
                "https://api.coinlore.net/api/ticker/?id=48543"
        );
        System.out.println(222);
        for (String s: cryptoList) {
            cryptoService.saveCrypto(getGsonCrypto(s));
        }
    }

    private CryptoDto getGsonCrypto(String crypto){
        List response = restTemplate.getForEntity(crypto, List.class).getBody();
        Gson gson = new Gson();
        String s = gson.toJson(response.get(0));
        return gson.fromJson(s, CryptoDto.class);
    }


}
