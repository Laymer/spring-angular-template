package be.julienbastin.template.security;

import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SecurityConfiguration {

    @Bean
    public RequestMatcher noSecurityMatcher() {
        return new OrRequestMatcher(
                PathPatternRequestMatcher.withDefaults().matcher("/v3/api-docs/**"),
                PathPatternRequestMatcher.withDefaults().matcher("/swagger-ui/**"),
                PathPatternRequestMatcher.withDefaults().matcher("/actuator/**")
        );
    }

    @Bean
    @NonNull
    public SecurityFilterChain apiFilterChain(@NonNull HttpSecurity http, @NonNull RequestMatcher noSecurityMatcher) throws Exception {
        RequestMatcher notNoSecurityMatcher = new NegatedRequestMatcher(noSecurityMatcher);
        RequestMatcher allPathsMatcher = PathPatternRequestMatcher.withDefaults().matcher("/**");
        RequestMatcher requestMatcher = new AndRequestMatcher(allPathsMatcher, notNoSecurityMatcher);
        return http
                .securityMatcher(requestMatcher)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
//                        .authenticated()) //TODO
                        .permitAll())
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
//                .oauth2Login(Customizer.withDefaults()) TODO
                .build();
    }

    @Bean
    @NonNull
    public SecurityFilterChain actuatorFilterChain(@NonNull HttpSecurity http, @NonNull RequestMatcher noSecurityMatcher) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(noSecurityMatcher)
                        .permitAll())
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

}
