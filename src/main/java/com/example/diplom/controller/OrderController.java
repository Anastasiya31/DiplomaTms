package com.example.diplom.controller;

import com.example.diplom.dto.OrderDTO;
import com.example.diplom.dto.InvoiceDTO;
import com.example.diplom.dto.OrderDTO;
import com.example.diplom.entity.Order;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.service.CountryService;
import com.example.diplom.service.CurrencyService;
import com.example.diplom.service.OrderService;
import com.example.diplom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    /** Все заказы */
    @GetMapping("/orders")
    public ModelAndView getOrders(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        return new ModelAndView("orders");
    }

    /** Просмотр информации */
    @GetMapping("order/{id}")
    public ModelAndView showOrder(@PathVariable Long id, Model model) throws EntityNotFountException {
        model.addAttribute("order", orderService.getOrderById(id));
        addModelAttributes(model);
        return new ModelAndView("orderShow");
    }

    /** Создание нового*/
    @RequestMapping("/new")
    public ModelAndView newOrder(Model model) {
        model.addAttribute("order", new OrderDTO());
        addModelAttributes(model);
        return new ModelAndView("orderForm");
    }
    /** Сохранение в БД */
    @PostMapping("/new")
    public ModelAndView newOrder(@ModelAttribute OrderDTO orderDTO, BindingResult bindingResult, Model model) {
        model.addAttribute("order", orderDTO);
        orderService.createOrder(orderDTO);
        addModelAttributes(model);
        return new ModelAndView("orderShow");
    }

    @PutMapping("/update/{id}")
    public Order updateOrder(@PathVariable("id") Long id,
                             @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(id, orderDTO);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return new ModelAndView("redirect:/order/orders");
    }

    private final CurrencyService currencyService;
    private final ProductService productService;
    private void addModelAttributes(Model model) {
        model.addAttribute("currencies", currencyService.getCurrencies());
        model.addAttribute("products", productService.getProducts());
    }
}
