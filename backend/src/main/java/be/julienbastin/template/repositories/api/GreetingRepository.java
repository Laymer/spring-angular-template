package be.julienbastin.template.repositories.api;

import be.julienbastin.template.models.Greeting;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface GreetingRepository {

    @NonNull
    Optional<Greeting> findGreetingByKey(@NonNull String key);
}
