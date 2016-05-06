//package CoursesEE.ModuleNumberFive.src.main.java;

//import CoursesEE.ModuleNumberFive.src.main.java.Calculator;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Main {
    private static final Logger log = Logger.getLogger(Calculator.class);
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
//        new ClassPathXmlApplicationContext("applicationContext.xml");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your expression separated by spaces. Example:\"4 + 6\":");
        String[] expression = scanner.nextLine().split(" ");

        Calculator calculator = (Calculator) applicationContext.getBean("calculator");

        for (int i = 0; i < expression.length; i++) {
            String key = expression[i];
            if ((calculator.getMap().containsKey(key)) && (i > 0) && (i < expression.length)) {
                String index1 = expression[i - 1];
                String index2 = expression[i + 1];
                log.info("true ----------------------------------------------------------------");
                Number result = calculator.getMap().get(key).execute(index1, index2);
                System.out.println(expression[i - 1] + " " + key + " " + expression[i + 1] + " = " + result);
                expression[i + 1] = result.toString();
                i = i + 1;
            }
        }

    }
}
