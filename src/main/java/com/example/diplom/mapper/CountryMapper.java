package com.example.diplom.mapper;

import com.example.diplom.dto.CountryDTO;
import com.example.diplom.entity.Country;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryMapper {

    public Country countryDtoToCountry(CountryDTO countryDTO) {
        return Country.builder()
                .id(countryDTO.getId())
                .country(countryDTO.getCountry())
                .build();
    }

    public CountryDTO countryToCountryDTO(Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .country(country.getCountry())
                .build();
    }
}
