package spring.training.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import spring.training.dao.DaoException;

@Component
@Aspect
public class ExceptionHandlingAspect {

	@AfterThrowing(pointcut = "execution(* spring..ProductDao.*(..))", throwing = "ex")
	public void convertToDaoException(Throwable ex) throws DaoException {
		throw new DaoException(ex);
	}
}
