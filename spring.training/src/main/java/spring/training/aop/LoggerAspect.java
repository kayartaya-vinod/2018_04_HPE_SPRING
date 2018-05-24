package spring.training.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
	
	public LoggerAspect() {
		System.out.println("LoggerAspect() called.");
	}
	
	@Pointcut("execution(* spring..ProductDao.getById(..))")
	public void pc1() {}
	
	@Pointcut("execution(* spring..ProductDao.getMany(..))")
	public void pc2() {}
	
	@Before("pc1() || pc2()")
	public void print(JoinPoint jp) {
		String methodName = jp.getSignature().getName() ;
		Object[]args = jp.getArgs();
		
		System.out.println(">>>>> " + methodName + " called with arguments: " + Arrays.toString(args));
	}

}








