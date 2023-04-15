package myapp.com.bookstore.security.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails user1 = User.builder()
                .username("user1")
                .password("{noop}password1")
                .roles("CUSTOMER")
                .build();

        UserDetails user3 = User.builder()
                .username("user3")
                .password("{noop}password3")
                .roles("CUSTOMER", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user3);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
//                        authorize.anyRequest().authenticated()
                                authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PATCH, "/api/v1/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                                        .requestMatchers("/api/v2/auth/**").permitAll()
                                        .requestMatchers((new AntPathRequestMatcher("/h2-console/**"))).permitAll()
                                        .anyRequest().authenticated()


                );

        http.httpBasic().and()
                .headers().disable();

        return http.build();
    }
}
