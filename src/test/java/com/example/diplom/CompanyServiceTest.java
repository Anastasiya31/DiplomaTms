package com.example.diplom;

import com.example.diplom.dto.CompanyDTO;
import com.example.diplom.entity.Company;
import com.example.diplom.entity.Country;
import com.example.diplom.mapper.CompanyMapper;
import com.example.diplom.repository.CompanyRepository;
import com.example.diplom.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @MockBean
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyService companyService;

    private Company company;

    @BeforeEach
    public void setup() {
        companyRepository = Mockito.mock(CompanyRepository.class);
        companyService = new CompanyService(companyRepository);
        company = Company.builder()
                //.id(1L)
                .companyTitle("111")
                .country(new Country())
                .email("fff")
                .tel("111")
                .build();
    }

    @Test
    @DisplayName("Testing saving Company success")
    void testSaveCompanySuccess() {

        given(companyRepository.save(company)).willReturn(company);
        // when -  action or the behaviour that we are going test
        Company savedCompany = companyService.createCompany(CompanyMapper.companyToCompanyDTO(company));
        // then - verify the output
        assertThat(savedCompany).isNotNull();
    }
//
//    @DisplayName("JUnit test for saveCompany method which throws exception")
//    @Test
//    public void givenExistingEmail_whenSaveCompany_thenThrowsException() {
//        // given - precondition or setup
//        given(companyRepository.findById(company.getId()))
//                .willReturn(Optional.of(company));
//        // when -  action or the behaviour that we are going test
//        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFountException.class, () -> {
//            companyService.createCompany(CompanyMapper.companyToCompanyDTO(company));
//        });
//        // then
//        verify(companyRepository, never()).save(any(Company.class));
//    }

    @DisplayName("JUnit test for getAllCompanys method")
    @Test
    public void givenCompanysList_whenGetAllCompanys_thenReturnCompanysList() {
        // given - precondition or setup

        Company company1 = Company.builder()
                .id(2L)
                .companyTitle("222")
                .country(new Country())
                .email("qqq")
                .tel("222")
                .build();

        given(companyRepository.findAll()).willReturn(List.of(company, company1));
        // when -  action or the behaviour that we are going test
        List<CompanyDTO> companyList = companyService.getCompanies();
        // then - verify the output
        assertThat(companyList).isNotNull();
        assertThat(companyList.size()).isEqualTo(2);
    }

    // JUnit test for getAllCompanys method
    @DisplayName("JUnit test for getAllCompanys method (negative scenario)")
    @Test
    public void givenEmptyCompanysList_whenGetAllCompanys_thenReturnEmptyCompanysList() {
        Company company1 = Company.builder()
                .id(2L)
                .companyTitle("222")
                .country(new Country())
                .email("qqq")
                .tel("222")
                .build();

        given(companyRepository.findAll()).willReturn(Collections.emptyList());
        // when -  action or the behaviour that we are going test
        List<CompanyDTO> companyList = companyService.getCompanies();
        // then - verify the output
        assertThat(companyList).isEmpty();
        assertThat(companyList.size()).isEqualTo(0);
    }

    // JUnit test for updateCompany method
    @DisplayName("JUnit test for updateCompany method")
    @Test
    public void givenCompanyObject_whenUpdateCompany_thenReturnUpdatedCompany() {
        // given - precondition or setup
        Long id = 3L;
        given(companyRepository.save(company)).willReturn(company);
        company.setId(id);
        company.setEmail("ram@gmail.com");
        company.setCompanyTitle("Ram");
        // when -  action or the behaviour that we are going test
        Company updatedCompany = companyService.updateCompany(id, CompanyMapper.companyToCompanyDTO(company));
        // then - verify the output
        assertThat(updatedCompany.getEmail()).isEqualTo("ram@gmail.com");
        assertThat(updatedCompany.getCompanyTitle()).isEqualTo("Ram");
    }

    // JUnit test for deleteCompany method
    @DisplayName("JUnit test for deleteCompany method")
    @Test
    public void givenCompanyId_whenDeleteCompany_thenNothing() {
        // given - precondition or setup
        long companyId = 1L;

        willDoNothing().given(companyRepository).deleteById(companyId);
        // when -  action or the behaviour that we are going test
        companyService.deleteCompany(companyId);
        // then - verify the output
        verify(companyRepository, times(1)).deleteById(companyId);
    }

    // JUnit test for getCompanyById method
    @DisplayName("JUnit test for getCompanyById method")
    @Test
    public void givenCompanyId_whenGetCompanyById_thenReturnCompanyObject() {

       Company company1 = Company.builder()
                .id(2L)
                .companyTitle("11144")
                .country(new Country())
                .email("ff55f")
                .tel("11661")
                .build();
        Long companyId = 2L;
        // given
        given(companyRepository.findById(companyId)).willReturn(Optional.of(company1));
        // when
        CompanyDTO savedCompany = companyService.getCompanyById(companyId);
        // then
        assertThat(savedCompany).isNotNull();
    }
}


//
//    @Test
//    @DisplayName("Testing saving Company failed")
//    void testSaveCompanyFailed() {
//        Company expected = new Company(1L, "OOO \"Interstal\"", new Country(1L, "Беларусь"), "+375441312311", "interstal@gmail.com");
//        Company actualCompany = companyService.createCompany(CompanyMapper.companyToCompanyDTO(expected));
//        Assertions.assertNotEquals(expected, actualCompany);
//    }
//
//    @Test
//    @DisplayName("Testing updating Company success")
//    void testUpdateCompanySuccess() throws EntityNotFountException {
//        Long id = 1L;
//        Company expected = new Company(1L, "OOO \"Interstal\"", new Country(1L, "Беларусь"), "+375441312311", "interstal@gmail.com");
//        companyService.updateCompany(1L,CompanyMapper.companyToCompanyDTO(expected));
//        Company actualCompany =  companyService.getCompanyById(id);
//        Assertions.assertEquals(expected, actualCompany);
//    }
//
//    @Test
//    @DisplayName("Testing updating Company failed")
//    void testUpdateCompanyFailed() throws EntityNotFountException {
//        Long id = 100L;
//        Company expected = new Company(1L, "OOO \"Interstal\"", new Country(1L, "Беларусь"), "+375441312311", "interstal@gmail.com");
//        companyService.updateCompany(1L,CompanyMapper.companyToCompanyDTO(expected));
//        Company actualCompany =  companyService.getCompanyById(id);
//        Assertions.assertNotEquals(expected, actualCompany);
//    }
//
//    @Test
//    @DisplayName("Testing delete Company which exists in database")
//    @Tag("Company-TEST")
//    void testDeleteCompanySuccess() throws EntityNotFountException {
//        Long id = 1L;
//        companyService.deleteCompany(id);
//        Company actualCompany = companyService.getCompanyById(1L);
//        Assertions.assertNull(actualCompany);
//    }
//    @Test
//    @DisplayName("Testing delete Company which exists in database")
//    @Tag("Company-TEST")
//    void testDeleteCompanyFailed() throws EntityNotFountException {
//        Long id = 1L;
//        companyService.deleteCompany(id);
//        Company actualCompany = companyService.getCompanyById(id);
//        Assertions.assertNotEquals(null,actualCompany);
//    }

//
//    @Test
//    @ParameterizedTest
//    @DisplayName("Testing search Company which exists in database")
//    @Tag("Company-TEST")
//    @ValueSource(ints = {1})
//    void FindCompanyByIdExists() throws EntityNotFountException {
//        Long id = 1L;
//        CompanyDTO actual = CompanyMapper.companyToCompanyDTO(companyService.getCompanyById(1L));
////        Mockito.when(companyRepository.getById(id)).thenReturn(null);
////
////        Company res = companyService.getCompanyById(id);
//
//        CompanyDTO expected = new CompanyDTO();
//        Assertions.assertAll(()->{
//        Assertions.assertEquals(expected.getCompanyTitle(),actual.getCompanyTitle());
//            Assertions.assertEquals(expected.getTel(),actual.getTel());
//        });
//    }
//
//    @Test
//    @DisplayName("Testing search Company which non exists in database")
//    @Tag("Company-TEST")
//    void FindCompanyByIdNotExists(){
//        Long id = 1L;
//        CompanyDTO actual = CompanyMapper.companyToCompanyDTO(companyService.getCompanyById(1L));
//        CompanyDTO expected = new CompanyDTO(1L, "OOO \"Interstal\"", new Country(1L, "Беларусь"), "+375441312311", "interstal@gmail.com");
//        Assertions.assertAll(()->{
//            Assertions.assertNotEquals(expected.getCompanyTitle(),actual.getCompanyTitle());
//            Assertions.assertNotEquals(expected.getTel(),actual.getTel());
//
//        });
//    }
//

