package com.example.diplom.service;

import com.example.diplom.dto.CompanyDTO;
import com.example.diplom.entity.Company;
import com.example.diplom.mapper.CompanyMapper;
import com.example.diplom.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<CompanyDTO> getCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(CompanyMapper::companyToCompanyDTO)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public Company createCompany(CompanyDTO companyDTO) {
//        Optional<Company> savedCompany = companyRepository.findById(companyDTO.getId());
//        if (savedCompany.isPresent()) {
//            throw new EntityNotFountException("Company already exist with given title:" + companyDTO.getCompanyTitle());
//        }
        return companyRepository.save(CompanyMapper.companyDtoToCompany(companyDTO));
    }

    public Company updateCompany(Long id, CompanyDTO companyDTO) {
        return companyRepository.findById(id)
                .map(company -> {
                    CompanyMapper.companyDtoToCompany(companyDTO);
                    return companyRepository.save(company);
                })
                .orElseGet(() -> {
                    companyDTO.setId(id);
                    return companyRepository.save(CompanyMapper.companyDtoToCompany(companyDTO));
                });
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @SneakyThrows
    public CompanyDTO getCompanyById(Long id) {
        //     return companyRepository.findById(id).orElse(null);
//        Company company;
//        Optional<Company> companyOptional = companyRepository.findById(id);
//        if (companyOptional.isPresent()) {
//            company = companyOptional.get();
//        } else {
//            throw new EntityNotFountException("Company with id: " + id + " was not found");
//        }
//        return Optional.of(company);
//             return companyRepository.findById(id);
        Company company = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company with id: " + id + " was not found"));
        return CompanyMapper.companyToCompanyDTO(company);

    }
}

