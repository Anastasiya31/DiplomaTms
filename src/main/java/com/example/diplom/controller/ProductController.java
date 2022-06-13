package com.example.diplom.controller;

import com.example.diplom.dto.ProductDTO;
import com.example.diplom.service.MetalCategoryService;
import com.example.diplom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    /**
     * Просмотр списка компаний
     */
    @GetMapping("/products")
    public ModelAndView getProducts(Model model) {
        model.addAttribute("products", productService.getProducts());
        return new ModelAndView("products");
    }
    @GetMapping("/productsData")
    public ModelAndView getProductsData(Model model) {
        model.addAttribute("products", productService.getProducts());
        return new ModelAndView("productsData");
    }

    /** Cписок для клиентов**/
    @GetMapping("/productsUser")
    public ModelAndView getProductsUser(Model model) {
        model.addAttribute("products", productService.getProducts());
        return new ModelAndView("userProducts");
    }

    /** Просмотр информации */
    @GetMapping("product/{id}")
    public ModelAndView showInvoice(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        addModelAttributes(model);
        return new ModelAndView("productShow");
    }

    /** Создание нового*/
    @GetMapping("/new")
    public ModelAndView newProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        addModelAttributes(model);
        return new ModelAndView("productForm");
    }

    /** Сохранение в БД */
    @PostMapping("/save")
    public ModelAndView newCompany(@ModelAttribute("product") ProductDTO productDTO) {
        productService.createProduct(productDTO);
        return new ModelAndView("productShow");
    }

    /**Обновление**/
    @RequestMapping("/update/{id}")
    public ModelAndView updateCompany(@PathVariable Long id, Model model, @ModelAttribute("product") ProductDTO productDTO) {
        model.addAttribute("product", productService.updateProduct(id, productDTO));
        addModelAttributes(model);
        // companyService.updateCompany(id, companyDTO );
        return new ModelAndView("productForm");
    }
    /**
     * Delete
     **/
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


//    @RequestMapping("/edit/{id}")
//public ModelAndView updateCompany(@PathVariable Long id, Model model, @ModelAttribute("product") ProductDTO productDTO) {
////    model.addAttribute("company", companyService.updateCompany(id, companyDTO ));
////    addModelAttributes(model);
//    productService.updateProduct(productDTO);
//    return new ModelAndView("productForm");
//}

//    @DeleteMapping("/products/{id}")
//    public void deleteProduct(@PathVariable("id") Long id) {
//        productService.deleteProduct(id);
//    }