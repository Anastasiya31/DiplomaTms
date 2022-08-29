package com.example.diplom.controller;

import com.example.diplom.dto.OrderDTO;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.service.CurrencyService;
import com.example.diplom.service.OrderService;
import com.example.diplom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    /**
     * Все заказы
     */
    @GetMapping("/orders")
    public ModelAndView getOrders(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        return new ModelAndView("orders");
    }
    @GetMapping("/ordersUser")
    public ModelAndView getOrdersUser(Model model) {
        model.addAttribute("orders", orderService.getOrdersUser());
        return new ModelAndView("orders");
    }

    @GetMapping("/ordersAdmin")
    public ModelAndView getOrdersAdmin(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        return new ModelAndView("orderAdmin");
    }

    /**
     * Просмотр информации
     */
    @GetMapping("order/{id}")
    public ModelAndView showOrder(@PathVariable Long id, Model model) throws EntityNotFountException {
        model.addAttribute("order", orderService.getOrderById(id));
        addModelAttributes(model);
        return new ModelAndView("orderShow");
    }

    /**
     * Создание нового
     */
    @GetMapping("/new")
    public ModelAndView newOrder(Model model) {
        model.addAttribute("order", new OrderDTO());
        addModelAttributes(model);
        return new ModelAndView("orderForm");
    }

    /**
     * Сохранение в БД
     */
    @PostMapping("/save")
    public ModelAndView newOrder(@ModelAttribute("order") OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
        return new ModelAndView("orderShow");
    }

    /**Обновление**/
    @RequestMapping("/update/{id}")
    public ModelAndView updateCompany(@PathVariable("id") Long id, Model model, @ModelAttribute("company") OrderDTO orderDTO) {
        model.addAttribute("order", orderService.updateOrder(id, orderDTO));
        addModelAttributes(model);
        // companyService.updateCompany(id, companyDTO );
        return new ModelAndView("companyForm");
    }
    /**
     * Удаление
     **/
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
