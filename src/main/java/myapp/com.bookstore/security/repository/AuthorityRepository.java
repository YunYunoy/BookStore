package myapp.com.bookstore.security.repository;

import myapp.com.bookstore.security.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findAllByRole(String role);

}
