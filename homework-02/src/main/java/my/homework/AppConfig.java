package my.homework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("my.homework")
public class AppConfig {

    @Bean
    public ProductService productService(){
        return new ProductService();
    }

    @Bean
    @Scope("prototype")
    public Cart cart(){
        return new Cart();
    }
}
