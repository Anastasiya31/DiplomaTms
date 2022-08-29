package com.example.diplom.service;//package com.example.diplom.service;
//
//import com.example.diplom.dto.InvoiceTypeDTO;
//import com.example.diplom.entity.InvoiceType;
//import com.example.diplom.exception.EntityNotFountException;
//import com.example.diplom.mapper.InvoiceTypeMapper;
//import com.example.diplom.repository.InvoiceTypeRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class InvoiceTypeService {
//    private final InvoiceTypeRepository invoiceTypeRepository;
//
//    public List<InvoiceTypeDTO> getInvoiceTypes() {
//        return invoiceTypeRepository.findAll()
//                .stream().map(InvoiceTypeMapper::invoiceTypeToInvoiceTypeDTO).collect(Collectors.toList());
//    }
//
//    public InvoiceType createInvoiceType(InvoiceTypeDTO invoiceTypeDTO) {
//        return invoiceTypeRepository.save(InvoiceTypeMapper.invoiceTypeDtoToInvoiceType(invoiceTypeDTO));
//    }
//
//    public InvoiceType updateInvoiceType(Long id, InvoiceTypeDTO invoiceTypeDTO) {
//        return invoiceTypeRepository.findById(id)
//                .map(invoiceType -> {
//                    InvoiceTypeMapper.invoiceTypeDtoToInvoiceType(invoiceTypeDTO);
//                    return invoiceTypeRepository.save(invoiceType);
//                })
//                .orElseGet(() -> {
//                    invoiceTypeDTO.setId(id);
//                    return invoiceTypeRepository.save(InvoiceTypeMapper.invoiceTypeDtoToInvoiceType(invoiceTypeDTO));
//                });
//    }
//
//    public void deleteInvoiceType(Long id) {
//        invoiceTypeRepository.deleteById(id);
//    }
//
//    public InvoiceType getInvoiceTypeById(Long id) throws EntityNotFountException {
//        InvoiceType invoiceType;
//        Optional<InvoiceType> invoiceTypeOptional = invoiceTypeRepository.findById(id);
//
//        if (invoiceTypeOptional.isPresent()) {
//            invoiceType = invoiceTypeOptional.get();
//        } else {
//            throw new EntityNotFountException("InvoiceType with id: " + id + " was not found");
//        }
//
//        return invoiceType;
//    }
//}
