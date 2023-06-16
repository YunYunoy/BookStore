package myapp.com.bookstore.services;

import myapp.com.bookstore.model.BookDTO;
import myapp.com.bookstore.enums.BookGenre;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    List<BookDTO> listBooks();

    Page<BookDTO> listBooksPageable(String title, BookGenre bookGenre, Integer pageNumber, Integer pageSize);

    Optional<BookDTO> getBookById(UUID id);

    BookDTO saveNewBook(BookDTO book);

    Optional<BookDTO> updateBookById(UUID id, BookDTO book);

    Boolean deleteBookById(UUID id);

    void patchBookById(UUID id, BookDTO book);
}
