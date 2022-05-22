package com.example.diplom.aspects;

import com.example.diplom.entity.Product;
import com.example.diplom.entity.Product;
import com.example.diplom.exception.EntityNotFountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductServiceAspects {
    @Pointcut("execution (* com.example.diplom.service.ProductService.createProduct(..))")
    public void createProductPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.ProductService.updateProduct(..))")
    public void updateProductPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.ProductService.deleteProduct(..))")
    public void deleteProductPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.ProductService.getProductById(..))")
    public void getProductByIdPointcut() {
    }

    @Around("createProductPointcut() || updateProductPointcut() || deleteProductPointcut()")
    public Object aroundAdviceTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Before ProductMethod ");
        Object proceed = proceedingJoinPoint.proceed();

        System.out.println("After ProductMethod ");
        return proceed;
    }

    @AfterReturning(value = "getProductByIdPointcut()", returning = "product")
    public void afterGetEmployee(Product product) {
        System.out.println("Return product:  " + product.getGrade() + " and " + product.getType());
    }

    @AfterThrowing(value = "getProductByIdPointcut()", throwing = "exception")
    public void afterThrowingGetEmployee(EntityNotFountException exception) {
        System.out.println("Throwing exception: " + exception.getMessage());
    }
}
