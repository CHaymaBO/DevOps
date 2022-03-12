package com.esprit.examen.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private static final Logger l = LogManager.getLogger(LoggingAspect.class);

	
	@After("execution(* com.esprit.examen.services.IsessionService.*(..))") // pointer sur package service
	public void logMethodExit(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		l.info("méthode : " + name +" exécutée avec succès");
	}
	@Around("execution(* com.esprit.examen.services.IsessionService.*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis(); // recupuer le temps en milisec
		Object obj = pjp.proceed();
		String name = pjp.getSignature().getName();
		long elapsedTime = System.currentTimeMillis() - start;
		l.info("Method execution: " + name +"Method execution time: " + elapsedTime + " milliseconds.");
		return obj;
	}

}
