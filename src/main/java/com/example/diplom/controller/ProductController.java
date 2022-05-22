package com.example.diplom.controller;

import com.example.diplom.dto.CompanyDTO;
import com.example.diplom.dto.OrderDTO;
import com.example.diplom.dto.ProductDTO;
import com.example.diplom.entity.Product;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.service.CurrencyService;
import com.example.diplom.service.MetalCategoryService;
import com.example.diplom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    /**
     * Просмотр списка компаний
     */
    @GetMapping("/products")
    public ModelAndView getCompanies(Model model) {
        model.addAttribute("products", productService.getProducts());
        return new ModelAndView("products");
    }


    /** Просмотр информации */
    @GetMapping("product/{id}")
    public ModelAndView showInvoice(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        addModelAttributes(model);
        return new ModelAndView("productShow");
    }

    /** Создание нового*/

    @RequestMapping("/new")
    public ModelAndView newProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        addModelAttributes(model);
        return new ModelAndView("productForm");
    }

    /** Сохранение в БД */
    @PostMapping("/new")
    public ModelAndView newProduct(@ModelAttribute ProductDTO productDTO, BindingResult bindingResult, Model model) {
        //  if (bindingResult.hasErrors()) {
        model.addAttribute("product", productDTO);
        productService.createProduct(productDTO);
        addModelAttributes(model);
        return new ModelAndView("productShow");
//        } else {
//            productService.createProduct(productDTO);
//            return new ModelAndView("companies");
//        }
    }


    @PutMapping("/products/{id}")
    public Product updateEmployee(@PathVariable("id") Long id,
                                  @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

//    @DeleteMapping("/products/{id}")
//    public void deleteProduct(@PathVariable("id") Long id) {
//        productService.deleteProduct(id);
//    }
    @RequestMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ModelAndView("redirect:/product/products");
    }

    private final MetalCategoryService metalCategoryService;
    private void addModelAttributes(Model model) {
        model.addAttribute("metalCategories", metalCategoryService.getMetalCategories());
    }
}
