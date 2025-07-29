package be.julienbastin.template.properties;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;

@ConfigurationProperties("pattern.security")
public record SecurityProperties(@NonNull @NotNull String allowedCors) {

    public String[] allowedCorsUrls() {
        return allowedCors.split(";");
    }
}
