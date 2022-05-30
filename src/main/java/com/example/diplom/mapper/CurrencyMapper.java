package com.example.diplom.mapper;

import com.example.diplom.dto.CurrencyDTO;
import com.example.diplom.entity.Currency;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CurrencyMapper {
    public Currency currencyDtoToCurrency(CurrencyDTO currencyDTO) {
        return Currency.builder()
                .id(currencyDTO.getId())
                .code(currencyDTO.getCode())
                .build();
    }

    public CurrencyDTO currencyToCurrencyDTO(Currency currency) {
        return CurrencyDTO.builder()
                .id(currency.getId())
                .code(currency.getCode())
                .build();
    }
}

