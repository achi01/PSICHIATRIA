package psichiatria.be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
public class SecurityDevConfig {

    @Bean
    public SecurityFilterChain devSecurity(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ← modo aggiornato per disabilitare CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
