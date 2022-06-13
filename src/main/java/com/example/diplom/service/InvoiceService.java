package com.example.diplom.service;

import com.example.diplom.dto.InvoiceDTO;
import com.example.diplom.entity.Invoice;
import com.example.diplom.mapper.InvoiceMapper;
import com.example.diplom.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public List<InvoiceDTO> getInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(InvoiceMapper::invoiceToInvoiceDTO)
                .collect(Collectors.toList());
    }

    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        return invoiceRepository.save(InvoiceMapper.invoiceDtoToInvoice(invoiceDTO));
    }

    public Invoice updateInvoice(Long id, InvoiceDTO invoiceDTO) {
        return invoiceRepository.findById(id)
                .map(invoice -> {
                    InvoiceMapper.invoiceDtoToInvoice(invoiceDTO);
                    return invoiceRepository.save(invoice);
                })
                .orElseGet(() -> {
                    invoiceDTO.setId(id);
                    return invoiceRepository.save( InvoiceMapper.invoiceDtoToInvoice(invoiceDTO));
                });
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    public Invoice getInvoiceById(Long id){
                return invoiceRepository.findById(id).orElse(null);
    }
}
