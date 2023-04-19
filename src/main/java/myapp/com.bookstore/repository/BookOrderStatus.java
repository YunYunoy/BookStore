package myapp.com.bookstore.repository;

import myapp.com.bookstore.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookOrderStatus extends JpaRepository<BookOrder, UUID> {
}
