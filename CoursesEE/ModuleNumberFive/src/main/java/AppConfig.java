import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class AppConfig{

    @Bean
    public Calculator calculator(){
        HashMap<String, Multitasking> map = new HashMap<>();
        map.put("+", adding());
        map.put("-", subtracting());
        map.put("*", multiplication());
        map.put("/", division());
        Calculator calculator = new Calculator(map);
        return calculator;
    }
    @Bean
    public Adding adding(){
        return new Adding();
    }
    @Bean
    public Subtracting subtracting(){
        return new Subtracting();
    }
    @Bean
    public Multiplication multiplication(){
        return new Multiplication();
    }
    @Bean
    public Division division(){
        return new Division();
    }

}
