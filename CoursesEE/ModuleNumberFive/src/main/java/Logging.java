import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Logging {
    private static final Logger log = Logger.getLogger(Calculator.class);

    @Before("execution(* *.execute(..))")
    public void loggingBeforeAdvice()  {
        log.info("The new is method");
        System.out.println("Advice run. New method");
    }

    @After("execution(* Multitasking*.*(..))")
    public void loggingAfterAdvice(JoinPoint joinPoint) {
        System.out.println(joinPoint.toString());
        log.info("The end is method");
        System.out.println("End.");
    }
    @AfterReturning(pointcut = "execution(* Multitasking*.*(..))", returning="retVal")
    public void doAfterReturnningTask(Object retVal){
        System.out.println("Returning:" + retVal.toString());
        log.info("The calculator is result " + retVal.toString());
    }

    @AfterThrowing(pointcut = "execution(* Multitasking*.*(..))", throwing="ex")
    public void doAfterThrowingTask(Exception ex){
        System.out.println("There has been an exception: " + ex.toString());
        log.error("This messages is error: " + ex.toString());
    }

    public void beforeAdvice() {
        System.out.println("Going to calculator.");
        log.info("New method calculator");
    }

    public void afterAdvice() {
        System.out.println("End.");
        log.info("The end is method");
    }


    public void afterReturningAdvice(Object retVal) {
        System.out.println("Returning:" + retVal.toString());
        log.info("The calculator is result " + retVal.toString());
    }

    public void AfterThrowingAdvice(NumberFormatException ex) {
        System.out.println("There has been an exception: " + ex.toString());
        log.error("This messages is error: " + ex.toString());
    }

}
