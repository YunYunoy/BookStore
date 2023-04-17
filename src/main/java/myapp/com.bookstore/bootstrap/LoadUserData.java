package myapp.com.bookstore.bootstrap;

import lombok.extern.slf4j.Slf4j;

import myapp.com.bookstore.security.entity.User;
import myapp.com.bookstore.security.entity.Authority;
import myapp.com.bookstore.security.repository.AuthorityRepository;
import myapp.com.bookstore.security.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoadUserData implements ApplicationRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public LoadUserData(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        loadSecurityData();
    }

    private void loadSecurityData() {
        Authority admin = authorityRepository.save(Authority.builder().role("ADMIN").build());
        Authority userRole = authorityRepository.save(Authority.builder().role("USER").build());
        Authority customer = authorityRepository.save(Authority.builder().role("CUSTOMER").build());

        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authority(admin)
                .build());

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .authority(userRole)
                .build());

        userRepository.save(User.builder()
                .username("customer")
                .password(passwordEncoder.encode("customer"))
                .authority(customer)
                .build());

        log.debug("Users Loaded: " + userRepository.count());
    }
}
