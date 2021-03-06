package com.example.lab.lab.services;

import com.example.lab.lab.dto.CryptoDto;
import com.example.lab.lab.entity.Crypto;
import com.example.lab.lab.entity.User;
import com.example.lab.lab.repository.CryptoRepo;
import com.example.lab.lab.services.modelMapper.CryptoModelMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import org.slf4j.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoService {
    private final CryptoRepo cryptoRepo;
    private final UserMessager userMessager;

    public List<CryptoDto> getAllCrypto(){
        return cryptoRepo.findAll()
                .stream()
                .map(CryptoModelMapper::cryptoMapperToDto)
                .collect(Collectors.toList());
    }

    public CryptoDto getCryptoId(Long id){
        return CryptoModelMapper.cryptoMapperToDto(cryptoRepo.findByCryptoid(id));
    }

    public void saveCrypto(CryptoDto cryptoDto) {
        if (cryptoRepo.existsByName(cryptoDto.getName())){
            Crypto c = cryptoRepo.findByName(cryptoDto.getName());
                c.setPrice_btc(cryptoDto.getPrice_btc());
                c.setPrice_usd(cryptoDto.getPrice_usd());
            cryptoRepo.save(c);
            userMessager.notify(c);
        }else{
            cryptoRepo.save(CryptoModelMapper.cryptoMapperToEntity(cryptoDto));
        }

    }

}
