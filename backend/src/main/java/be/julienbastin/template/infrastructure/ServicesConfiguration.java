package be.julienbastin.template.infrastructure;

import be.julienbastin.template.repositories.api.GreetingRepository;
import be.julienbastin.template.services.api.HelloService;
import be.julienbastin.template.services.impl.HelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Bean
    public HelloService helloService(GreetingRepository greetingRepository) {
        return new HelloServiceImpl(greetingRepository);
    }

}
