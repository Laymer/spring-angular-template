package be.julienbastin.template.infrastructure;

import be.julienbastin.template.repositories.api.GreetingRepository;
import be.julienbastin.template.repositories.impl.GreetingRepositoryImpl;
import be.julienbastin.template.repositories.jpa.GreetingJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoriesConfiguration {

    @Bean
    public GreetingRepository greetingRepository(GreetingJpaRepository jpaRepository) {
        return new GreetingRepositoryImpl(jpaRepository);
    }

}
