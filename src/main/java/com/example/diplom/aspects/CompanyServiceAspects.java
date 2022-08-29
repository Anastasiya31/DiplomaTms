package com.example.diplom.aspects;

import com.example.diplom.entity.Company;
import com.example.diplom.exception.EntityNotFountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CompanyServiceAspects {

    private final Logger logger = LoggerFactory.getLogger(
            this.getClass());
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
       logger.info("Before CompanyMethod ");
        Object proceed = proceedingJoinPoint.proceed();

        logger.info("After CompanyMethod ");
        return proceed;
    }

    @AfterReturning(value = "getCompanyByIdPointcut()", returning = "company")
    public void afterGetEmployee(Company company) {
        logger.info("Return company:  " + company.getCompanyTitle() + "from :" + company.getCountry());
    }

    @AfterThrowing(value = "getCompanyByIdPointcut()", throwing = "exception")
    public void afterThrowingGetEmployee(EntityNotFountException exception) {
        logger.info("Throwing exception: " + exception.getMessage());
    }
}
