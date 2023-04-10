package myapp.com.bookstore.services;

import myapp.com.bookstore.entity.Book;
import myapp.com.bookstore.model.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    List<BookDTO> listBooks();
    Page<BookDTO> findAllPageable(Pageable pageable);

    Optional<BookDTO> getBookById(UUID id);

    BookDTO saveNewBook(BookDTO book);

    Optional<BookDTO> updateBookById(UUID id, BookDTO book);

    Boolean deleteBookById(UUID id);

    void patchBookById(UUID id, BookDTO book);
}
