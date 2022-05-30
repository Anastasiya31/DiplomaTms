package com.example.diplom.controller;

import com.example.diplom.dto.CountryDTO;
import com.example.diplom.entity.Country;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/countries")
    public List<CountryDTO> Countries() {
        return countryService.getCountries();
    }

    @PostMapping("/countries")
    public Country createCountry(@RequestBody CountryDTO countryDTO) {
        return countryService.createCountry(countryDTO);
    }

    @PutMapping("/countries/{id}")
    public Country updateCountry(@PathVariable Long id,
                                 @RequestBody CountryDTO countryDTO) {
        return countryService.updateCountry(id, countryDTO);
    }

    @DeleteMapping("/countries/{id}")
    public void deleteCountry(@PathVariable("id") Long id) {
        countryService.deleteCountry(id);
    }

    @GetMapping("/countries/{id}")
    public Country getById(@PathVariable("id") Long id) throws EntityNotFountException {
        return countryService.getCountryById(id);
    }
}
