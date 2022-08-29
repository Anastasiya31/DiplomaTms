package com.example.diplom;

import com.example.diplom.entity.*;
import com.example.diplom.exception.EntityNotFountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class InvoiceServiceTest {
    static InvoiceService invoiceService;

    @ParameterizedTest
    @DisplayName("Testing search Invoice which exists in database")
    @Tag("Invoice-TEST")
    @ValueSource(ints = {1})
    void FindInvoiceByIdExists() throws EntityNotFountException {
        Long id = 1L;
        InvoiceDTO actual = InvoiceMapper.invoiceToInvoiceDTO(invoiceService.getInvoiceById(1L));
        InvoiceDTO expected = new InvoiceDTO();
        Assertions.assertAll(() -> {
            Assertions.assertEquals(expected.getData(), actual.getData());
            Assertions.assertEquals(expected.getPaymentDetails(), actual.getPaymentDetails());
        });
    }

    @Test
    @DisplayName("Testing search Invoice which non exists in database")
    @Tag("Invoice-TEST")
    void FindInvoiceByIdNotExists() throws EntityNotFountException {
        Long id = 1L;
        InvoiceDTO actual = InvoiceMapper.invoiceToInvoiceDTO(invoiceService.getInvoiceById(1L));
        InvoiceDTO expected = new InvoiceDTO(1L, new Date(), "payment", new Order(), new Company(), InvoiceType.BUY);
        Assertions.assertAll(() -> {
            Assertions.assertNotEquals(expected.getData(), actual.getData());
            Assertions.assertNotEquals(expected.getPaymentDetails(), actual.getPaymentDetails());
        });
    }

    @Test
    @DisplayName("Testing saving Invoice success")
    void testSaveInvoiceSuccess() {
        Invoice expected = new Invoice(1L, new Date(), "payment", new Order(), new Company(), InvoiceType.BUY);
        Invoice actualInvoice = invoiceService.createInvoice(InvoiceMapper.invoiceToInvoiceDTO(expected));
        Assertions.assertEquals(expected, actualInvoice);
    }

    @Test
    @DisplayName("Testing saving Invoice failed")
    void testSaveInvoiceFailed() {
        Invoice expected = new Invoice(1L, new Date(), "payment", new Order(), new Company(), InvoiceType.BUY);
        Invoice actualInvoice = invoiceService.createInvoice(InvoiceMapper.invoiceToInvoiceDTO(expected));
        Assertions.assertNotEquals(expected, actualInvoice);
    }

    @Test
    @DisplayName("Testing updating Invoice success")
    void testUpdateInvoiceSuccess() throws EntityNotFountException {
        Long id = 1L;
        Invoice expected = new Invoice(1L, new Date(), "payment", new Order(), new Company(), InvoiceType.BUY);
        invoiceService.updateInvoice(1L, InvoiceMapper.invoiceToInvoiceDTO(expected));
        Invoice actualInvoice = invoiceService.getInvoiceById(id);
        Assertions.assertEquals(expected, actualInvoice);
    }

    @Test
    @DisplayName("Testing updating Invoice failed")
    void testUpdateInvoiceFailed() throws EntityNotFountException {
        Long id = 100L;
        Invoice expected = new Invoice(1L, new Date(), "payment", new Order(), new Company(), InvoiceType.BUY);
        invoiceService.updateInvoice(1L, InvoiceMapper.invoiceToInvoiceDTO(expected));
        Invoice actualInvoice = invoiceService.getInvoiceById(id);
        Assertions.assertNotEquals(expected, actualInvoice);
    }

    @Test
    @DisplayName("Testing delete Invoice which exists in database")
    @Tag("Invoice-TEST")
    void testDeleteInvoiceSuccess() throws EntityNotFountException {
        Long id = 1L;
        invoiceService.deleteInvoice(id);
        Invoice actualInvoice = invoiceService.getInvoiceById(1L);
        Assertions.assertNull(actualInvoice);
    }

    @Test
    @DisplayName("Testing delete Invoice which exists in database")
    @Tag("Invoice-TEST")
    void testDeleteInvoiceFailed() throws EntityNotFountException {
        Long id = 1L;
        invoiceService.deleteInvoice(id);
        Invoice actualInvoice = invoiceService.getInvoiceById(id);
        Assertions.assertNotEquals(null, actualInvoice);
    }
}
