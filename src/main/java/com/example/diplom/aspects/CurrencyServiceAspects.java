package com.example.diplom.aspects;

import com.example.diplom.entity.Currency;
import com.example.diplom.exception.EntityNotFountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CurrencyServiceAspects {
    @Pointcut("execution (* com.example.diplom.service.CurrencyService.createCurrency(..))")
    public void createCurrencyPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.CurrencyService.updateCurrency(..))")
    public void updateCurrencyPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.CurrencyService.deleteCurrency(..))")
    public void deleteCurrencyPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.CurrencyService.getCurrencyById(..))")
    public void getCurrencyByIdPointcut() {
    }

    @Around("createCurrencyPointcut() || updateCurrencyPointcut() || deleteCurrencyPointcut()")
    public Object aroundAdviceTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Before CurrencyMethod ");
        Object proceed = proceedingJoinPoint.proceed();

        System.out.println("After CurrencyMethod ");
        return proceed;
    }

    @AfterReturning(value = "getCurrencyByIdPointcut()", returning = "currency")
    public void afterGetEmployee(Currency currency) {
        System.out.println("Return currency:  " + currency.getCode());
    }

    @AfterThrowing(value = "getCurrencyByIdPointcut()", throwing = "exception")
    public void afterThrowingGetEmployee(EntityNotFountException exception) {
        System.out.println("Throwing exception: " + exception.getMessage());
    }
}
