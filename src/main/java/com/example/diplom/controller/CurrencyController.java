package com.example.diplom.controller;

import com.example.diplom.dto.CurrencyDTO;
import com.example.diplom.entity.Currency;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public List<CurrencyDTO> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @PostMapping("/currencies")
    public Currency createCurrency(@RequestBody CurrencyDTO currencyDTO) {
        return currencyService.createCurrency(currencyDTO);
    }

    @PutMapping("/currencies/{id}")
    public Currency updateCurrency(@PathVariable("id") Long id,
                                   @RequestBody CurrencyDTO currencyDTO) {
        return currencyService.updateCurrency(id, currencyDTO);
    }

    @DeleteMapping("/currencies/{id}")
    public void deleteCurrency(@PathVariable("id") Long id) {
        currencyService.deleteCurrency(id);
    }

    @GetMapping("/currencies/{id}")
    public Currency getById(@PathVariable("id") Long id) throws EntityNotFountException {
        return currencyService.getCurrencyById(id);
    }
}
