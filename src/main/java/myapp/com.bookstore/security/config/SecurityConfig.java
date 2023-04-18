package myapp.com.bookstore.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll()
                            .requestMatchers("/api/v2/**").permitAll()
                            .requestMatchers((new AntPathRequestMatcher("/h2-console/**"))).permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                            .requestMatchers("/api/v3/**").hasAnyRole("ADMIN", "EMPLOYEE")
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasAnyRole("ADMIN", "EMPLOYEE")
                            .requestMatchers(HttpMethod.POST, "/api/v1/**").hasAnyRole("ADMIN", "EMPLOYEE")
                            .requestMatchers(HttpMethod.PATCH, "/api/v1/**").hasAnyRole("ADMIN", "EMPLOYEE")
                            .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyRole("ADMIN", "EMPLOYEE");
                })
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic()
                .and().csrf().disable();


        http.headers().frameOptions().sameOrigin();

        return http.build();
    }
}
