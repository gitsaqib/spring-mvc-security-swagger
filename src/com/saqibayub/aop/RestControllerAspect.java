package com.saqibayub.aop;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
@Aspect
@EnableAspectJAutoProxy
public class RestControllerAspect {

	@Before("execution(public * com.saqibayub.controller.*Controller.*(..))")
	public void logBeforeAnyControllerCall(JoinPoint joinPoint) throws UnknownHostException, ParseException, JsonProcessingException {
		
		String method = joinPoint.getTarget().toString();
		System.out.println("RestControllerAspect method : "+method); //FIXME log back
		String parameters = Arrays.toString(joinPoint.getArgs());
		System.out.println("RestControllerAspect parameters : "+parameters); //FIXME log back 
	}
}
