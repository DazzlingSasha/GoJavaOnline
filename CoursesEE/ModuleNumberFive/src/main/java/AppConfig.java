import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.HashMap;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AppConfig {

    @Bean
    public Calculator calculator() {
        HashMap<String, Multitasking> map = new HashMap<>();
        map.put("+", adding());
        map.put("-", subtracting());
        map.put("*", multiplication());
        map.put("/", division());
        Calculator calculator = new Calculator(map);
        return calculator;
    }

    @Bean
    public Multitasking adding() {
        return new Adding();
    }

    @Bean
    public Multitasking subtracting() {
        return new Subtracting();
    }

    @Bean
    public Multitasking multiplication() {
        return new Multiplication();
    }

    @Bean
    public Multitasking division() {
        return new Division();
    }

    @Bean
    public Logging logging() {
        return new Logging();
    }
}
