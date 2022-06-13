//package com.example.diplom;
//
//import com.example.diplom.dto.CountryDTO;
//import com.example.diplom.entity.Country;
//import com.example.diplom.exception.EntityNotFountException;
//import com.example.diplom.mapper.CountryMapper;
//import com.example.diplom.repository.CountryRepository;
//import com.example.diplom.service.CountryService;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.willDoNothing;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class CountryServiceTest {
//
//    @MockBean
//    private CountryRepository countryRepository;
//    @InjectMocks
//    private CountryService countryService;
//
//    private Country country;
//
//    @BeforeEach
//    public void setup() {
//        countryRepository = Mockito.mock(CountryRepository.class);
//        countryService = new CountryService(countryRepository);
//        country = Country.builder()
//                //.id(1L)
//                .country("Bel")
//                .build();
//    }
//
//    @Test
//    @DisplayName("Testing saving Country success")
//    void testSaveCountrySuccess() {
//
//        given(countryRepository.save(country)).willReturn(country);
//        // when -  action or the behaviour that we are going test
//        Country savedCountry = countryService.createCountry(CountryMapper.countryToCountryDTO(country));
//        // then - verify the output
//        assertThat(savedCountry).isNotNull();
//    }
//
//    @DisplayName("JUnit test for saveCountry method which throws exception")
//    @Test
//    public void givenExistingEmail_whenSaveCountry_thenThrowsException() {
//        // given - precondition or setup
//        given(countryRepository.findById(country.getId()))
//                .willReturn(Optional.of(country));
//        // when -  action or the behaviour that we are going test
//        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFountException.class, () -> {
//            countryService.createCountry(CountryMapper.countryToCountryDTO(country));
//        });
//        // then
//        verify(countryRepository, never()).save(any(Country.class));
//    }
//
//    @DisplayName("JUnit test for getAllCountrys method")
//    @Test
//    public void givenCountrysList_whenGetAllCountrys_thenReturnCountrysList() {
//        // given - precondition or setup
//
//        Country country1 = Country.builder()
//                .id(2L)
//                .country("Rus")
//                .build();
//
//        given(countryRepository.findAll()).willReturn(List.of(country, country1));
//        // when -  action or the behaviour that we are going test
//        List<CountryDTO> countryList = countryService.getCountries();
//        // then - verify the output
//        assertThat(countryList).isNotNull();
//        assertThat(countryList.size()).isEqualTo(2);
//    }
//
//    // JUnit test for getAllCountrys method
//    @DisplayName("JUnit test for getAllCountrys method (negative scenario)")
//    @Test
//    public void givenEmptyCountrysList_whenGetAllCountrys_thenReturnEmptyCountrysList() {
//        Country country1 = Country.builder()
//                .id(2L)
//                .country("gg")
//                .build();
//
//        given(countryRepository.findAll()).willReturn(Collections.emptyList());
//        // when -  action or the behaviour that we are going test
//        List<CountryDTO> countryList = countryService.getCountries();
//        // then - verify the output
//        assertThat(countryList).isEmpty();
//        assertThat(countryList.size()).isEqualTo(0);
//    }
//
//    // JUnit test for updateCountry method
//    @DisplayName("JUnit test for updateCountry method")
//    @Test
//    public void givenCountryObject_whenUpdateCountry_thenReturnUpdatedCountry() {
//        // given - precondition or setup
//        Long id = 3L;
//        given(countryRepository.save(country)).willReturn(country);
//        country.setId(id);
//        country.setCountry("ffff");
//        // when -  action or the behaviour that we are going test
//        Country updatedCountry = countryService.updateCountry(id, CountryMapper.countryToCountryDTO(country));
//        // then - verify the output
//        assertThat(updatedCountry.getCountry()).isEqualTo("ffff");
//    }
//
//    // JUnit test for deleteCountry method
//    @DisplayName("JUnit test for deleteCountry method")
//    @Test
//    public void givenCountryId_whenDeleteCountry_thenNothing() {
//        // given - precondition or setup
//        long countryId = 1L;
//
//        willDoNothing().given(countryRepository).deleteById(countryId);
//        // when -  action or the behaviour that we are going test
//        countryService.deleteCountry(countryId);
//        // then - verify the output
//        verify(countryRepository, times(1)).deleteById(countryId);
//    }
//
//    // JUnit test for getCountryById method
//    @SneakyThrows
//    @DisplayName("JUnit test for getCountryById method")
//    @Test
//    public void givenCountryId_whenGetCountryById_thenReturnCountryObject() {
//
//        Country country1 = Country.builder()
//                .id(2L)
//                .country("ffff")
//                .build();
//        Long countryId = 2L;
//        // given
//        given(countryRepository.findById(countryId)).willReturn(Optional.of(country1));
//        // when
//        Country savedCountry = countryService.getCountryById(countryId);
//        // then
//        assertThat(savedCountry).isNotNull();
//    }
//}
//
//
//
////package com.example.diplom;
////
////import com.example.diplom.dto.CountryDTO;
////import com.example.diplom.entity.Country;
////import com.example.diplom.exception.EntityNotFountException;
////import com.example.diplom.mapper.CountryMapper;
////import com.example.diplom.service.CountryService;
////import org.junit.jupiter.api.Assertions;
////import org.junit.jupiter.api.DisplayName;
////import org.junit.jupiter.api.Tag;
////import org.junit.jupiter.api.Test;
////import org.junit.jupiter.params.ParameterizedTest;
////import org.junit.jupiter.params.provider.ValueSource;
////
////public class CountryServiceTest {
////    static CountryService countryService;
////
////    @ParameterizedTest
////    @DisplayName("Testing search Country which exists in database")
////    @Tag("CountryS-TEST")
////    @ValueSource(ints = {1})
////    void FindCountryByIdExists() throws EntityNotFountException {
////        Long id = 1L;
////        CountryDTO actual = CountryMapper.countryToCountryDTO(countryService.getCountryById(1L));
////        CountryDTO expected = new CountryDTO();
////        Assertions.assertAll(()->{
////            Assertions.assertEquals(expected.getCountry(),actual.getCountry());
////        });
////    }
////
////    @Test
////    @DisplayName("Testing search Country which non exists in database")
////    @Tag("Country-TEST")
////    void FindCountryByIdNotExists() throws EntityNotFountException {
////        Long id = 1L;
////        CountryDTO actual = CountryMapper.countryToCountryDTO(countryService.getCountryById(1L));
////        CountryDTO expected = new CountryDTO(1L, "www");
////        Assertions.assertAll(()->{
////            Assertions.assertNotEquals(expected.getCountry(),actual.getCountry());
////
////        });
////    }
////
////    @Test
////    @DisplayName("Testing saving Country success")
////    void testSaveCountrySuccess() {
////        Country expected = new Country(1L,"www");
////        Country actualCountry = countryService.createCountry(CountryMapper.countryToCountryDTO(expected));
////        Assertions.assertEquals(expected, actualCountry);
////    }
////
////    @Test
////    @DisplayName("Testing saving Country failed")
////    void testSaveCountryFailed() {
////        Country expected = new Country(1L, "www");
////        Country actualCountry = countryService.createCountry(CountryMapper.countryToCountryDTO(expected));
////        Assertions.assertNotEquals(expected, actualCountry);
////    }
////
////    @Test
////    @DisplayName("Testing updating Country success")
////    void testUpdateCountrySuccess() throws EntityNotFountException {
////        Long id = 1L;
////        Country expected = new Country(1L,"www");
////        countryService.updateCountry(1L,CountryMapper.countryToCountryDTO(expected));
////        Country actualCountry =  countryService.getCountryById(id);
////        Assertions.assertEquals(expected, actualCountry);
////    }
////
////    @Test
////    @DisplayName("Testing updating Country failed")
////    void testUpdateCountryFailed() throws EntityNotFountException {
////        Long id = 100L;
////        Country expected = new Country(1L,"www");
////        countryService.updateCountry(1L,CountryMapper.countryToCountryDTO(expected));
////        Country actualCountry =  countryService.getCountryById(id);
////        Assertions.assertNotEquals(expected, actualCountry);
////    }
////
////    @Test
////    @DisplayName("Testing delete Country which exists in database")
////    @Tag("Country-TEST")
////    void testDeleteCountrySuccess() throws EntityNotFountException {
////        Long id = 1L;
////        countryService.deleteCountry(id);
////        Country actualCountry = countryService.getCountryById(1L);
////        Assertions.assertNull(actualCountry);
////    }
////
////    @Test
////    @DisplayName("Testing delete Country which exists in database")
////    @Tag("Country-TEST")
////    void testDeleteCountryFailed() throws EntityNotFountException {
////        Long id = 1L;
////        countryService.deleteCountry(id);
////        Country actualCountry = countryService.getCountryById(id);
////        Assertions.assertNotEquals(null,actualCountry);
////    }
////}
