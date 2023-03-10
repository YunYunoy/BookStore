package myapp.com.bookstore.services;

import myapp.com.bookstore.model.BookDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    List<BookDTO> listBooks();

    Optional<BookDTO> getBookById(UUID id);

    BookDTO saveNewBook(BookDTO book);

    void updateBookById(UUID id, BookDTO book);

    void deleteBookById(UUID id);

    void patchBookById(UUID id, BookDTO book);
}
