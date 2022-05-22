package com.example.diplom.aspects;

import com.example.diplom.entity.Company;
import com.example.diplom.exception.EntityNotFountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CompanyServiceAspects {
    @Pointcut("execution (* com.example.diplom.service.CompanyService.createCompany(..))")
    public void createCompanyPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.CompanyService.updateCompany(..))")
    public void updateCompanyPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.CompanyService.deleteCompany(..))")
    public void deleteCompanyPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.CompanyService.getCompanyById(..))")
    public void getCompanyByIdPointcut() {
    }

    @Around("createCompanyPointcut() || updateCompanyPointcut() || deleteCompanyPointcut()")
    public Object aroundAdviceTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Before CompanyMethod ");
        Object proceed = proceedingJoinPoint.proceed();

        System.out.println("After CompanyMethod ");
        return proceed;
    }

    @AfterReturning(value = "getCompanyByIdPointcut()", returning = "company")
    public void afterGetEmployee(Company company) {
        System.out.println("Return company:  " + company.getCompanyTitle() + "from :" + company.getCountry());
    }

    @AfterThrowing(value = "getCompanyByIdPointcut()", throwing = "exception")
    public void afterThrowingGetEmployee(EntityNotFountException exception) {
        System.out.println("Throwing exception: " + exception.getMessage());
    }
}
