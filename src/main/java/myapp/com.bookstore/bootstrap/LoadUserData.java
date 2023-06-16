package myapp.com.bookstore.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import myapp.com.bookstore.auth.User;
import myapp.com.bookstore.auth.Authority;
import myapp.com.bookstore.repository.AuthorityRepository;
import myapp.com.bookstore.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoadUserData implements ApplicationRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        loadSecurityData();
    }

    private void loadSecurityData() {
        Authority admin = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
        Authority userRole = authorityRepository.save(Authority.builder().role("ROLE_USER").build());
        Authority employee = authorityRepository.save(Authority.builder().role("ROLE_EMPLOYEE").build());

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .authority(userRole)
                .build());

        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authority(admin)
                .build());

        userRepository.save(User.builder()
                .username("employee")
                .password(passwordEncoder.encode("employee"))
                .authority(employee)
                .build());

        log.debug("Users Loaded: " + userRepository.count());
    }
}
