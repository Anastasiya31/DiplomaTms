package com.example.diplom.aspects;

import com.example.diplom.entity.Invoice;
import com.example.diplom.exception.EntityNotFountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InvoiceServiceAspects {
    @Pointcut("execution (* com.example.diplom.service.InvoiceService.createInvoice(..))")
    public void createInvoicePointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.InvoiceService.updateInvoice(..))")
    public void updateInvoicePointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.InvoiceService.deleteInvoice(..))")
    public void deleteInvoicePointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.InvoiceService.getInvoiceById(..))")
    public void getInvoiceByIdPointcut() {
    }

    @Around("createInvoicePointcut() || updateInvoicePointcut() || deleteInvoicePointcut()")
    public Object aroundAdviceTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Before InvoiceMethod ");
        Object proceed = proceedingJoinPoint.proceed();

        System.out.println("After InvoiceMethod ");
        return proceed;
    }

    @AfterReturning(value = "getInvoiceByIdPointcut()", returning = "invoice")
    public void afterGetEmployee(Invoice invoice) {
        System.out.println("Return invoice:  " + invoice.getPaymentDetails());
    }

    @AfterThrowing(value = "getInvoiceByIdPointcut()", throwing = "exception")
    public void afterThrowingGetEmployee(EntityNotFountException exception) {
        System.out.println("Throwing exception: " + exception.getMessage());
    }
}
