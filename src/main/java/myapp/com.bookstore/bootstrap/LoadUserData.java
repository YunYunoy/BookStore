package myapp.com.bookstore.bootstrap;

import myapp.com.bookstore.security.entity.User;
import myapp.com.bookstore.security.entity.Role;
import myapp.com.bookstore.security.repository.RoleRepository;
import myapp.com.bookstore.security.repository.UserRepository;
import myapp.com.bookstore.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class LoadUserData implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public LoadUserData(UserRepository userRepository, RoleRepository roleRepository, UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void run(ApplicationArguments args) {
        Role userRole = new Role();
        userRole.setName("USER");

        Role adminRole = new Role();
        adminRole.setName("ADMIN");

        roleRepository.saveAll(Arrays.asList(userRole, adminRole));

        User user = new User();
        user.setUsername("user");
        user.setEmail("user@gmail.com");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        user.setRoles(Collections.singleton(userRole));

        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(new BCryptPasswordEncoder().encode("password"));
        admin.setRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));

        userRepository.saveAll(Arrays.asList(user, admin));

        System.out.println("users: " + userRepository.count() + "\n" +
                userRepository.findByUsername(user.getUsername()).toString() + "\n" +
                userRepository.findByUsername(admin.getUsername()).toString());
    }
}
