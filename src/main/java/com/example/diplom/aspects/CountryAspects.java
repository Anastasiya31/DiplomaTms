package com.example.diplom.aspects;

import com.example.diplom.entity.Country;
import com.example.diplom.entity.Country;
import com.example.diplom.exception.EntityNotFountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CountryAspects {

    @Pointcut("execution (* com.example.diplom.service.CountryService.createCountry(..))")
    public void createCountryPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.CountryService.updateCountry(..))")
    public void updateCountryPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.CountryService.deleteCountry(..))")
    public void deleteCountryPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.CountryService.getCountryById(..))")
    public void getCountryByIdPointcut() {
    }

    @Around("createCountryPointcut() || updateCountryPointcut() || deleteCountryPointcut()")
    public Object aroundAdviceTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Before CountryMethod ");
        Object proceed = proceedingJoinPoint.proceed();

        System.out.println("After CountryMethod ");
        return proceed;
    }

    @AfterReturning(value = "getCountryByIdPointcut()", returning = "country")
    public void afterGetEmployee(Country country) {
        System.out.println("Return country:  " + country.getCountry());
    }

    @AfterThrowing(value = "getCountryByIdPointcut()", throwing = "exception")
    public void afterThrowingGetEmployee(EntityNotFountException exception) {
        System.out.println("Throwing exception: " + exception.getMessage());
    }
}
