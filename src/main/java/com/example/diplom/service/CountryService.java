package com.example.diplom.service;

import com.example.diplom.dto.CountryDTO;
import com.example.diplom.entity.Country;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.mapper.CountryMapper;
import com.example.diplom.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryService{

    private final CountryRepository countryRepository;

    public List<CountryDTO> getCountries() {
        return countryRepository.findAll()
                .stream()
                .map(CountryMapper::countryToCountryDTO)
                .collect(Collectors.toList());
    }

    public Country createCountry(CountryDTO countryDTO) {
        return countryRepository.save(CountryMapper.countryDtoToCountry(countryDTO));
    }

    public Country updateCountry(Long id, CountryDTO countryDTO) {

        return countryRepository.findById(id)
                .map(country -> {
                    CountryMapper.countryDtoToCountry(countryDTO);
                    return countryRepository.save(country);
                })
                .orElseGet(() -> {
                    countryDTO.setId(id);
                    return countryRepository.save(CountryMapper.countryDtoToCountry(countryDTO));
                });
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }

    public Country getCountryById(Long id) throws EntityNotFountException {
        Country country;
        Optional<Country> companyOptional = countryRepository.findById(id);
        if (companyOptional.isPresent()) {
            country = companyOptional.get();
        } else {
            throw new EntityNotFountException("Country with id: " + id + " was not found");
        }
        return country;
    }
}
