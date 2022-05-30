package com.example.diplom.controller;

import com.example.diplom.dto.InvoiceDTO;
import com.example.diplom.entity.Invoice;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.service.CompanyService;
import com.example.diplom.service.InvoiceService;
import com.example.diplom.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    /** Просмотр списка invoice */
    @GetMapping("/invoices")
    public ModelAndView getInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.getInvoices());
        return new ModelAndView("invoices");
    }

    /** Просмотр информации */
    @GetMapping("invoice/{id}")
    public ModelAndView showInvoice(@PathVariable Long id, Model model) throws EntityNotFountException {
        model.addAttribute("invoice", invoiceService.getInvoiceById(id));
        addModelAttributes(model);
        return new ModelAndView("invoiceShow");
    }


    /** Создание нового*/
    @RequestMapping("/new")
    public ModelAndView newInvoice(Model model) {
        model.addAttribute("invoice", new InvoiceDTO());
        addModelAttributes(model);
        return new ModelAndView("invoiceForm");
    }

    /** Сохранение в БД */
    @PostMapping("/new")
    public ModelAndView newInvoice(@ModelAttribute InvoiceDTO invoiceDTO, BindingResult bindingResult, Model model) {
        //  if (bindingResult.hasErrors()) {
        model.addAttribute("invoice", invoiceDTO);
        invoiceService.createInvoice(invoiceDTO);
        addModelAttributes(model);
        return new ModelAndView("invoiceShow");
//        } else {
//            invoiceService.createInvoice(invoiceDTO);
//            return new ModelAndView("companies");
//        }
    }
    
    @PutMapping("/update/{id}")
    public Invoice updateInvoice(@PathVariable("id") Long id,
                                 @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(id, invoiceDTO);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteInvoice(id);
        return new ModelAndView("redirect:/invoice/invoices");
    }

    private final CompanyService companyService;
    private final OrderService orderService;
    private void addModelAttributes(Model model) {
        model.addAttribute("companies", companyService.getCompanies());
        model.addAttribute("orders", orderService.getOrders());
    }


}
