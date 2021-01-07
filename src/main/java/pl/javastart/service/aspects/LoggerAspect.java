package pl.javastart.service.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import pl.javastart.model.Book;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    @Before("pl.javastart.service.aspects.AspectUtil.allBookRepositoryMethods()")
    public void logInfoBefore(JoinPoint joinPoint) {

        System.out.printf("Log before %s with args: %s\n",
                joinPoint.getSignature(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @After("pl.javastart.service.aspects.AspectUtil.allBookRepositoryMethods()")
    public void logInfoAfter() {
        System.out.println("Method executed ");
    }

    @AfterThrowing("pl.javastart.service.aspects.AspectUtil.allBookRepositoryMethods()")
    public void logError() {
        System.out.println("Method finished with error");
    }

    @AfterReturning("pl.javastart.service.aspects.AspectUtil.allBookRepositoryMethods()")
        public void logSuccess(){
            System.out.println("Method successfully returned");
        }


    @AfterReturning(pointcut = "pl.javastart.service.aspects.AspectUtil.getBookRepository() && args(isbn)",
            returning = "result")
    public void logSuccess(JoinPoint joinPoint, String isbn, Book result) {
        if(result != null)
            System.out.printf("Method get() successfully returned value %s for isbn %s\n", result, isbn);
    }
}
