package com.example.diplom.aspects;

import com.example.diplom.entity.MetalCategory;
import com.example.diplom.exception.EntityNotFountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetalCategoryServiceAspects {
    @Pointcut("execution (* com.example.diplom.service.MetalCategoryService.createMetalCategory(..))")
    public void createMetalCategoryPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.MetalCategoryService.updateMetalCategory(..))")
    public void updateMetalCategoryPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.MetalCategoryService.deleteMetalCategory(..))")
    public void deleteMetalCategoryPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.MetalCategoryService.getMetalCategoryById(..))")
    public void getMetalCategoryByIdPointcut() {
    }

    @Around("createMetalCategoryPointcut() || updateMetalCategoryPointcut() || deleteMetalCategoryPointcut()")
    public Object aroundAdviceTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Before MetalCategoryMethod ");
        Object proceed = proceedingJoinPoint.proceed();

        System.out.println("After MetalCategoryMethod ");
        return proceed;
    }

    @AfterReturning(value = "getMetalCategoryByIdPointcut()", returning = "metalCategory")
    public void afterGetEmployee(MetalCategory metalCategory) {
        System.out.println("Return metalCategory:  " + metalCategory.getCategory());
    }

    @AfterThrowing(value = "getMetalCategoryByIdPointcut()", throwing = "exception")
    public void afterThrowingGetEmployee(EntityNotFountException exception) {
        System.out.println("Throwing exception: " + exception.getMessage());
    }
}
