import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        ClassPathXmlApplicationContext("applicationContext.xml");
        double s = 10.;
        String d = "10.";
        System.out.println(Double.parseDouble(d));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your expression separated by spaces. Example:\"4 + 6\":");
        String[] expression = scanner.nextLine().split(" ");

        Calculator calculator = (Calculator) applicationContext.getBean("calculator");

        for (int i = 0; i < expression.length; i++) {
            String key = expression[i];
            if (calculator.map.containsKey(key) && i > 0 && i < expression.length) {
                String index1 = expression[i - 1];
                String index2 = expression[i + 1];

                Number result = calculator.map.get(key).execute(index1, index2);
                System.out.println(expression[i - 1] + " " + key + " " + expression[i + 1] + " = " + result);
                expression[i + 1] = result.toString();
                i = i + 1;
            }
        }

    }
}
