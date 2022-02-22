package com.example.lab.lab.controllers;

import com.example.lab.lab.dto.CryptoDto;
import com.example.lab.lab.entity.Crypto;
import com.example.lab.lab.repository.UserRepo;
import com.example.lab.lab.services.CryptoService;
import com.example.lab.lab.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crypto")
@RequiredArgsConstructor
public class CryptoController {

    final CryptoService cryptoService;
    final UserServices userServices;

    @GetMapping("/getAll")
    public List<CryptoDto> getAllCrypto(){
        return cryptoService.getAllCrypto();
    }

    @GetMapping("/cryptoCode")
    public CryptoDto getCryptoId(@RequestParam Long id){
        return cryptoService.getCryptoId(id);
    }

    @GetMapping("/notify")
    public void notifyCrypto(@RequestParam String name, @RequestParam String symbol){
        userServices.userRegistryOnSymbol(name, symbol);
    }

}
