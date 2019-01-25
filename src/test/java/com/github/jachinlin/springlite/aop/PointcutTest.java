package com.github.jachinlin.springlite.aop;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

import com.github.jachinlin.springlite.aop.aspectj.AspectJExpressionPointcut;
import com.github.jachinlin.springlite.service.PetStoreService;

public class PointcutTest {
	@Test
	public void testPointcut() throws Exception{
		
		String expression = "execution(* com.github.jachinlin.springlite.service.*.placeOrder(..))";
		
		Pointcut pc = new AspectJExpressionPointcut();
		pc.setExpression(expression);
		
		
		
		Class<?> targetClass = PetStoreService.class;
		
		Method method1 = targetClass.getMethod("placeOrder");		
		assertTrue(pc.matches(method1));
		
		Method method2 = targetClass.getMethod("getAccount");		
		assertFalse(pc.matches(method2));
		
		
		
		
	}
}
