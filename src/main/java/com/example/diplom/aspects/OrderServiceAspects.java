package com.example.diplom.aspects;

import com.example.diplom.entity.Order;
import com.example.diplom.entity.Order;
import com.example.diplom.entity.Order;
import com.example.diplom.exception.EntityNotFountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class OrderServiceAspects {
    @Pointcut("execution (* com.example.diplom.service.OrderService.createOrder(..))")
    public void createOrderPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.OrderService.updateOrder(..))")
    public void updateOrderPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.OrderService.deleteOrder(..))")
    public void deleteOrderPointcut() {
    }

    @Pointcut("execution (* com.example.diplom.service.OrderService.getOrderById(..))")
    public void getOrderByIdPointcut() {
    }

    @Around("createOrderPointcut() || updateOrderPointcut() || deleteOrderPointcut()")
    public Object aroundAdviceTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Before OrderMethod ");
        Object proceed = proceedingJoinPoint.proceed();

        System.out.println("After OrderMethod ");
        return proceed;
    }

    @AfterReturning(value = "getOrderByIdPointcut()", returning = "order")
    public void afterGetEmployee(Order order) {
        System.out.println("Return order:  " + order.getProduct() + " = " + order.getPrice());
    }

    @AfterThrowing(value = "getOrderByIdPointcut()", throwing = "exception")
    public void afterThrowingGetEmployee(EntityNotFountException exception) {
        System.out.println("Throwing exception: " + exception.getMessage());
    }
}
