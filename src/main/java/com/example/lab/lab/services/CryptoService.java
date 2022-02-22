package com.example.lab.lab.services;

import com.example.lab.lab.dto.CryptoDto;
import com.example.lab.lab.entity.Crypto;
import com.example.lab.lab.entity.User;
import com.example.lab.lab.repository.CryptoRepo;
import com.example.lab.lab.services.modelMapper.CryptoModelMapper;
import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import org.slf4j.Logger;
import java.util.stream.Collectors;

@Service
public class CryptoService {
    Logger logger = LoggerFactory.getLogger(CryptoService.class);

    @Autowired
    private CryptoRepo cryptoRepo;

    final RestTemplate restTemplate = new RestTemplate();

    public List<CryptoDto> getAllCrypto(){
        List<Crypto> cryptos =  cryptoRepo.findAll();
        return cryptos.stream().map(CryptoModelMapper::cryptoMapperToDto).collect(Collectors.toList());
    }

    public CryptoDto getCryptoId(Long id){
        Crypto crypto = cryptoRepo.findByCryptoid(id);
        return CryptoModelMapper.cryptoMapperToDto(crypto);
    }

    public void notifyCryptoPrice(String name, String symbol){
        Crypto crypto = cryptoRepo.findBySymbol(symbol);
        User user = new User();
        user.setCrypto(crypto);
        user.setUserName(name);
        user.setPrice(crypto.getPrice_usd());
        List<User> users = crypto.getUserSet();
        users.add(user);
        crypto.setUserSet(users);
        cryptoRepo.save(crypto);
    }

    @Scheduled(fixedRate = 1000 * 60)
    private void createSchedule(){
        List<String> cryptoList = List.of(
                "https://api.coinlore.net/api/ticker/?id=90",
                "https://api.coinlore.net/api/ticker/?id=80",
                "https://api.coinlore.net/api/ticker/?id=48543"
        );
        for (String s: cryptoList) {
            saveCrypto(getGsonCrypto(s));
        }

    }

    private CryptoDto getGsonCrypto(String crypto){
            ResponseEntity<List> response = restTemplate.getForEntity(crypto, List.class);
            List products = response.getBody();
            Gson gson = new Gson();
            String s = gson.toJson(products.get(0));
            CryptoDto cryptoDto =  gson.fromJson(s, CryptoDto.class);
            return cryptoDto;
    }

    public void saveCrypto(CryptoDto cryptoDto){
        Crypto c = cryptoRepo.findByName(cryptoDto.getName());
        Crypto crypto = CryptoModelMapper.cryptoMapperToEntity(cryptoDto);

        if (c == null){
            c = CryptoModelMapper.cryptoMapperToEntity(cryptoDto);
            cryptoRepo.save(c);
        }else {
            c.setPrice_btc(crypto.getPrice_btc());
            c.setPrice_usd(crypto.getPrice_usd());
            cryptoRepo.save(c);
            checkerPrice(c);
        }

    }

    private void checkerPrice(Crypto crypto){
        List<User> users = crypto.getUserSet();
        for (User u : users) {
            double percent = (crypto.getPrice_usd() / u.getPrice()) * 100 - 100;
            if (crypto.getPrice_usd() > (u.getPrice() + (u.getPrice()/100)) ||
                    (crypto.getPrice_usd() + crypto.getPrice_usd()/100) < u.getPrice()){
                logger.warn(u.getUserName() + ":" + " " + crypto.getCryptoid() + " " + crypto.getSymbol() + " " +
                        + percent);
            }
        }
    }

}
