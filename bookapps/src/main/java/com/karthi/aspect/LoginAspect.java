package com.karthi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {
	@Around("execution(* com.karthi.service.*.*(..))")
	
		public Object login(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("Before advised method execution " + jp.getSignature().getName());
		Object result = jp.proceed();
		System.out.println("After advised method execution " + jp.getSignature().getName());
		return result;
	}
}

