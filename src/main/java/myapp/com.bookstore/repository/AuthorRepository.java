package myapp.com.bookstore.repository;

import myapp.com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
