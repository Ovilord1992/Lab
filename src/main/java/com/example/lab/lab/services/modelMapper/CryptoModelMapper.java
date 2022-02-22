package com.example.lab.lab.services.modelMapper;

import com.example.lab.lab.dto.CryptoDto;
import com.example.lab.lab.entity.Crypto;
import org.springframework.stereotype.Component;

@Component
public class CryptoModelMapper {

    public static Crypto cryptoMapperToEntity(CryptoDto cryptoDto){
        return new Crypto(
                cryptoDto.getId(),
                cryptoDto.getSymbol(),
                cryptoDto.getName(),
                cryptoDto.getNameid(),
                cryptoDto.getRank(),
                cryptoDto.getPrice_usd(),
                cryptoDto.getPrice_btc()
        );
    }

    public static CryptoDto cryptoMapperToDto(Crypto crypto){
        return new CryptoDto(
                crypto.getCryptoid(),
                crypto.getSymbol(),
                crypto.getName(),
                crypto.getNameid(),
                crypto.getRank(),
                crypto.getPrice_usd(),
                crypto.getPrice_btc()
        );
    }

}
