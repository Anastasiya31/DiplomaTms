package com.example.diplom.mapper;

import com.example.diplom.dto.InvoiceDTO;
import com.example.diplom.entity.Invoice;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InvoiceMapper {
    public Invoice invoiceDtoToInvoice(InvoiceDTO invoiceDTO) {
        return Invoice.builder()
                .id(invoiceDTO.getId())
                .paymentDetails(invoiceDTO.getPaymentDetails())
                .invoiceType(invoiceDTO.getInvoiceType())
                .order(invoiceDTO.getOrder())
                .company(invoiceDTO.getCompany())
                .data(invoiceDTO.getData())
                .build();
    }

    public InvoiceDTO invoiceToInvoiceDTO(Invoice invoice) {
        return InvoiceDTO.builder()
                .id(invoice.getId())
                .paymentDetails(invoice.getPaymentDetails())
                .invoiceType(invoice.getInvoiceType())
                .order(invoice.getOrder())
                .company(invoice.getCompany())
                .data(invoice.getData())
                .build();
    }

}