//package com.example.diplom.controller;
//
//import com.example.diplom.dto.InvoiceTypeDTO;
//import com.example.diplom.entity.InvoiceType;
//import com.example.diplom.exception.EntityNotFountException;
//import com.example.diplom.service.InvoiceTypeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/invoiceType")
//public class InvoiceTypeController {
//
//    private final InvoiceTypeService invoiceTypeService;
//
//    @GetMapping("/invoiceTypes")
//    public List<InvoiceTypeDTO> getInvoiceTypes() {
//        return invoiceTypeService.getInvoiceTypes();
//    }
//
//    @PostMapping("/invoiceTypes")
//    public InvoiceType createInvoiceType(@RequestBody InvoiceTypeDTO invoiceTypeDTO) {
//        return invoiceTypeService.createInvoiceType(invoiceTypeDTO);
//    }
//
//    @PutMapping("/invoiceTypes/{id}")
//    public InvoiceType updateInvoiceType(@PathVariable("id") Long id,
//                                         @RequestBody InvoiceTypeDTO invoiceTypeDTO) {
//        return invoiceTypeService.updateInvoiceType(id, invoiceTypeDTO);
//    }
//
//    @DeleteMapping("/invoiceTypes/{id}")
//    public void deleteInvoiceType(@PathVariable("id") Long id) {
//        invoiceTypeService.deleteInvoiceType(id);
//    }
//
//    @GetMapping("/invoiceTypes/{id}")
//    public InvoiceType getById(@PathVariable("id") Long id) throws EntityNotFountException {
//        return invoiceTypeService.getInvoiceTypeById(id);
//    }
//}
