package com.example.diplom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class AppController {
//	@Autowired
//	private ProductService service;

	// Login form

	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping("/userDashboard")
	public ModelAndView userDashboard() {
		return new ModelAndView("indexUser");
	}

	@RequestMapping("/adminDashboard")
	public ModelAndView adminDashboard() {
		return new ModelAndView("index");
	}

//	@RequestMapping("/")
//	public String viewHomePage(Model model) {
//		List<Product> listProducts = service.listAll();
//		model.addAttribute("listProducts", listProducts);
//
//		return "index";
//	}
//
//	@RequestMapping("/new")
//	public String showNewProductForm(Model model) {
//		Product product = new Product();
//		model.addAttribute("product", product);
//
//		return "new_product";
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public String saveProduct(@ModelAttribute("product") Product product) {
//		service.save(product);
//
//		return "redirect:/";
//	}
//
//	@RequestMapping("/edit/{id}")
//	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
//		ModelAndView mav = new ModelAndView("edit_product");
//
//		Product product = service.get(id);
//		mav.addObject("product", product);
//
//		return mav;
//	}
//
//	@RequestMapping("/delete/{id}")
//	public String deleteProduct(@PathVariable(name = "id") Long id) {
//		service.delete(id);
//
//		return "redirect:/";
//	}
}
