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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableMethodSecurity
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    protected void config(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        provider.setUserDetailsService(userDetailsService);
//        return provider;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll()
                            .requestMatchers("/api/v2/**").permitAll()
                            .requestMatchers((new AntPathRequestMatcher("/h2-console/**"))).permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PATCH, "/api/v1/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .httpBasic();

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }


//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        UserDetails user1 = User.builder()
//                .username("user")
//                .password("{noop}password")
//                .roles("USER")
//                .build();
//
//        UserDetails user3 = User.builder()
//                .username("admin")
//                .password("{noop}password")
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user3);
//    }


}
