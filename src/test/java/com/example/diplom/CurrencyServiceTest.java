package com.example.diplom;

import com.example.diplom.dto.CurrencyDTO;
import com.example.diplom.entity.Currency;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.mapper.CurrencyMapper;
import com.example.diplom.service.CurrencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CurrencyServiceTest {
    static CurrencyService currencyService;

    @ParameterizedTest
    @DisplayName("Testing search Currency which exists in database")
    @Tag("CurrencyS-TEST")
    @ValueSource(ints = {1})
    void FindCurrencyByIdExists() throws EntityNotFountException {
        Long id = 1L;
        CurrencyDTO actual = CurrencyMapper.currencyToCurrencyDTO(currencyService.getCurrencyById(1L));
        CurrencyDTO expected = new CurrencyDTO();
        Assertions.assertAll(()->{
            Assertions.assertEquals(expected.getCode(),actual.getCode());
        });
    }

    @Test
    @DisplayName("Testing search Currency which non exists in database")
    @Tag("Currency-TEST")
    void FindCurrencyByIdNotExists() throws EntityNotFountException {
        Long id = 1L;
        CurrencyDTO actual = CurrencyMapper.currencyToCurrencyDTO(currencyService.getCurrencyById(1L));
        CurrencyDTO expected = new CurrencyDTO(1L, "www");
        Assertions.assertAll(()->{
            Assertions.assertNotEquals(expected.getCode(),actual.getCode());

        });
    }

    @Test
    @DisplayName("Testing saving Currency success")
    void testSaveCurrencySuccess() {
        Currency expected = new Currency(1L,"www");
        Currency actualCurrency = currencyService.createCurrency(CurrencyMapper.currencyToCurrencyDTO(expected));
        Assertions.assertEquals(expected, actualCurrency);
    }

    @Test
    @DisplayName("Testing saving Currency failed")
    void testSaveCurrencyFailed() {
        Currency expected = new Currency(1L, "www");
        Currency actualCurrency = currencyService.createCurrency(CurrencyMapper.currencyToCurrencyDTO(expected));
        Assertions.assertNotEquals(expected, actualCurrency);
    }

    @Test
    @DisplayName("Testing updating Currency success")
    void testUpdateCurrencySuccess() throws EntityNotFountException {
        Long id = 1L;
        Currency expected = new Currency(1L,"www");
        currencyService.updateCurrency(1L,CurrencyMapper.currencyToCurrencyDTO(expected));
        Currency actualCurrency =  currencyService.getCurrencyById(id);
        Assertions.assertEquals(expected, actualCurrency);
    }

    @Test
    @DisplayName("Testing updating Currency failed")
    void testUpdateCurrencyFailed() throws EntityNotFountException {
        Long id = 100L;
        Currency expected = new Currency(1L,"www");
        currencyService.updateCurrency(1L,CurrencyMapper.currencyToCurrencyDTO(expected));
        Currency actualCurrency =  currencyService.getCurrencyById(id);
        Assertions.assertNotEquals(expected, actualCurrency);
    }

    @Test
    @DisplayName("Testing delete Currency which exists in database")
    @Tag("Currency-TEST")
    void testDeleteCurrencySuccess() throws EntityNotFountException {
        Long id = 1L;
        currencyService.deleteCurrency(id);
        Currency actualCurrency = currencyService.getCurrencyById(1L);
        Assertions.assertNull(actualCurrency);
    }
    @Test
    @DisplayName("Testing delete Currency which exists in database")
    @Tag("Currency-TEST")
    void testDeleteCurrencyFailed() throws EntityNotFountException {
        Long id = 1L;
        currencyService.deleteCurrency(id);
        Currency actualCurrency = currencyService.getCurrencyById(id);
        Assertions.assertNotEquals(null,actualCurrency);
    }
}
