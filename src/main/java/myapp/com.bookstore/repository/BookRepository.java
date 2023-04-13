package myapp.com.bookstore.repository;

import myapp.com.bookstore.entity.Book;
import myapp.com.bookstore.model.BookGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Page<Book> findAllByTitleIsLikeIgnoreCase(String title, Pageable pageable);

    Page<Book> findAllByBookGenre(BookGenre bookGenre, Pageable pageable);

    Page<Book> findAllByTitleIsLikeIgnoreCaseAndBookGenre(String title, BookGenre bookGenre, Pageable pageable);
}
