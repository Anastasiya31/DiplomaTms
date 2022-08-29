package com.example.diplom.controller;

import com.example.diplom.dto.CompanyDTO;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.service.CompanyService;
import com.example.diplom.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    /**
     * Просмотр списка компаний
     */
    @GetMapping("/companies")
    public ModelAndView getCompanies(Model model) {
        model.addAttribute("companies", companyService.getCompanies());
        return new ModelAndView("companies");
    }

    /**
     * Просмотр информации
     */
    @GetMapping("company/{id}")
    public ModelAndView showCompany(@PathVariable Long id, Model model) throws EntityNotFountException {
        model.addAttribute("company", companyService.getCompanyById(id));
        addModelAttributes(model);
        return new ModelAndView("companyShow");
    }

    /**
     * Создание нового
     */
    @GetMapping("/new")
    public ModelAndView newCompany(Model model) {
        model.addAttribute("company", new CompanyDTO());
        addModelAttributes(model);
        return new ModelAndView("companyForm");
    }

    /**
     * Сохранение в БД
     */
    @PostMapping("/save")
    public ModelAndView newCompany(@ModelAttribute("company") CompanyDTO companyDTO) {
        companyService.createCompany(companyDTO);
        return new ModelAndView("companyShow");
    }
/**Обновление**/
    @RequestMapping("/update/{id}")
    public ModelAndView updateCompany(@PathVariable Long id, Model model, @ModelAttribute("company") CompanyDTO companyDTO) {
        model.addAttribute("company", companyService.updateCompany(id, companyDTO));
        addModelAttributes(model);
        // companyService.updateCompany(id, companyDTO );
        return new ModelAndView("companyForm");
    }

    /**
     * Удаление
     **/
    @RequestMapping("/delete/{id}")
    public ModelAndView deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return new ModelAndView("redirect:/company/companies");
    }

    private final CountryService countryService;

    private void addModelAttributes(Model model) {
        model.addAttribute("countries", countryService.getCountries());
    }
}

//@RequestMapping("/update/{id}")
//    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
//        ModelAndView mav = new ModelAndView("editCompany");
//
//        Company company = companyService.getCompanyById(id);
//        mav.addObject("company", company);
//
//        return mav;
//    }

