package com.example.diplom.service;

import com.example.diplom.dto.CurrencyDTO;
import com.example.diplom.entity.Currency;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.mapper.CurrencyMapper;
import com.example.diplom.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public List<CurrencyDTO> getCurrencies() {
        return currencyRepository.findAll()
                .stream()
                .map(CurrencyMapper::currencyToCurrencyDTO)
                .collect(Collectors.toList());
    }

    public Currency createCurrency(CurrencyDTO currencyDTO) {
        return currencyRepository.save(CurrencyMapper.currencyDtoToCurrency(currencyDTO));
    }

    public Currency updateCurrency(Long id, CurrencyDTO currencyDTO) {
        return currencyRepository.findById(id)
                .map(currency -> {
                    CurrencyMapper.currencyDtoToCurrency(currencyDTO);
                    return currencyRepository.save(currency);
                })
                .orElseGet(() -> {
                    currencyDTO.setId(id);
                    return currencyRepository.save( CurrencyMapper.currencyDtoToCurrency(currencyDTO));
                });
    }

    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }

    public Currency getCurrencyById(Long id) throws EntityNotFountException {
        Currency currency;
        Optional<Currency> currencyOptional = currencyRepository.findById(id);

        if (currencyOptional.isPresent()) {
            currency = currencyOptional.get();
        } else {
            throw new EntityNotFountException("Currency with id: " + id + " was not found");
        }

        return currency;
    }
}

