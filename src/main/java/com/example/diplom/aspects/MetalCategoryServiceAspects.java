package com.example.diplom.aspects;

import com.example.diplom.entity.MetalCategory;
import com.example.diplom.exception.EntityNotFountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetalCategoryServiceAspects {

    private final Logger logger = LoggerFactory.getLogger(
            this.getClass());
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
        logger.info("Before MetalCategoryMethod ");
        Object proceed = proceedingJoinPoint.proceed();

        logger.info("After MetalCategoryMethod ");
        return proceed;
    }

    @AfterReturning(value = "getMetalCategoryByIdPointcut()", returning = "metalCategory")
    public void afterGetEmployee(MetalCategory metalCategory) {
        logger.info("Return metalCategory:  " + metalCategory.getCategory());
    }

    @AfterThrowing(value = "getMetalCategoryByIdPointcut()", throwing = "exception")
    public void afterThrowingGetEmployee(EntityNotFountException exception) {
        logger.info("Throwing exception: " + exception.getMessage());
    }
}
