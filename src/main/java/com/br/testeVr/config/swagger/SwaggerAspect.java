package com.br.testeVr.config.swagger;

import io.swagger.v3.oas.annotations.Parameter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;




@Aspect
@Component
public class SwaggerAspect {


    @Before("execution(public * .controller *(..))")
    public void processDefaultOperation(JoinPoint joinPoint) throws Throwable {
        Method method = getMethodFromJoinPoint(joinPoint).orElse(null);
        if (method != null) {
            DefaultOperation defaultOperation = AnnotationUtils.findAnnotation(method, DefaultOperation.class);
            if (defaultOperation != null) {
                Arrays.stream(defaultOperation.parameters()).forEach(this::processParameter);
            }
        }
    }


    private void processParameter(Parameter parameter) {
        System.out.printf("Processando parâmetro: %s%n", parameter.name());
    }


    private Optional<Method> getMethodFromJoinPoint(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Class<?> targetClass = joinPoint.getTarget().getClass();

        return Arrays.stream(targetClass.getMethods())
                .filter(m -> m.getName().equals(methodName))
                .findFirst();
    }
}

