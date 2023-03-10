package myapp.com.bookstore.repository;

import myapp.com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
