package spring.training.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ParamCheckAspect {

	@Around("execution(* spring..ProductDao.*(Double, Double))")
	public Object swapInputs(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Double d1 = (Double) args[0];
		Double d2 = (Double) args[1];
		if (d2 < d1) {
			args = new Object[] { d2, d1 };
		}
		return pjp.proceed(args);
	}
}
