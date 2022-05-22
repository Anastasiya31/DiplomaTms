package com.example.diplom.mapper;

import com.example.diplom.dto.CompanyDTO;
import com.example.diplom.entity.Company;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CompanyMapper {
    public Company companyDtoToCompany(CompanyDTO companyDTO) {
        return Company.builder()
                .id(companyDTO.getId())
                .companyTitle(companyDTO.getCompanyTitle())
                .country(companyDTO.getCountry())
                .email(companyDTO.getEmail())
                .tel(companyDTO.getTel())
                .build();
    }

    public CompanyDTO companyToCompanyDTO(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .companyTitle(company.getCompanyTitle())
                .country(company.getCountry())
                .email(company.getEmail())
                .tel(company.getTel())
                .build();
    }
}
