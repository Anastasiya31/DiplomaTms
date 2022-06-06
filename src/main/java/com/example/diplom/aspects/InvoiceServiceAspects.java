package com.example.diplom.aspects;

import com.example.diplom.entity.Invoice;
import com.example.diplom.exception.EntityNotFountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InvoiceServiceAspects {

    private final Logger logger = LoggerFactory.getLogger(
            this.getClass());
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
        logger.info("Before InvoiceMethod ");
        Object proceed = proceedingJoinPoint.proceed();

        logger.info("After InvoiceMethod ");
        return proceed;
    }

    @AfterReturning(value = "getInvoiceByIdPointcut()", returning = "invoice")
    public void afterGetEmployee(Invoice invoice) {
        logger.info("Return invoice:  " + invoice.getPaymentDetails());
    }

    @AfterThrowing(value = "getInvoiceByIdPointcut()", throwing = "exception")
    public void afterThrowingGetEmployee(EntityNotFountException exception) {
        logger.info("Throwing exception: " + exception.getMessage());
    }
}
