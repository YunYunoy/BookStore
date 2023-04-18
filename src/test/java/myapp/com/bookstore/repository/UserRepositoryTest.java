package myapp.com.bookstore.repository;

import myapp.com.bookstore.security.entity.User;
import myapp.com.bookstore.security.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testFindByUsername(){
        User user = User.builder()
                .username("test user")
                .password("test password")
                .build();
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsername("test user");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("test user");
        assertThat(foundUser.get().getPassword()).isEqualTo("test password");

    }
}
