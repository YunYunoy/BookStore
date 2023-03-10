package myapp.com.bookstore.services;

import myapp.com.bookstore.model.BookDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private Map<UUID, BookDTO> bookMap;

    public BookServiceImpl() {
        this.bookMap = new HashMap<>();

        BookDTO book1 = BookDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .bookGenre(BookDTO.BookGenre.FANTASY)
                .title("Witcher")
                .authors(Arrays.asList("Andrzej Sapkowski", "CD Projekt RED"))
                .publisher("Publisher 1")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BookDTO book2 = BookDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .bookGenre(BookDTO.BookGenre.SCIENCE)
                .title("Effective Java")
                .authors(Arrays.asList("Joshua Bloch"))
                .publisher("Publisher 2")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BookDTO book3 = BookDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .bookGenre(BookDTO.BookGenre.HORROR)
                .title("IT")
                .authors(Arrays.asList("Stephen King"))
                .publisher("Publisher 3")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        bookMap.put(book1.getId(), book1);
        bookMap.put(book2.getId(), book2);
        bookMap.put(book3.getId(), book3);
    }

    @Override
    public List<BookDTO> listBooks() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Optional<BookDTO> getBookById(UUID id) {
        return Optional.of(bookMap.get(id));
    }

    @Override
    public BookDTO saveNewBook(BookDTO book) {
        BookDTO savedBook = BookDTO.builder()
                .id(UUID.randomUUID())
                .publisher(book.getPublisher())
                .bookGenre(book.getBookGenre())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .version(1)
                .authors(book.getAuthors())
                .title(book.getTitle())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        bookMap.put(savedBook.getId(), savedBook);
        return savedBook;
    }

    @Override
    public void updateBookById(UUID id, BookDTO book) {
        BookDTO existing = bookMap.get(id);
        existing.setAuthors(book.getAuthors());
        existing.setBookGenre(book.getBookGenre());
        existing.setIsbn(book.getIsbn());
        existing.setPrice(book.getPrice());
        existing.setTitle(book.getTitle());
        existing.setPublisher(book.getPublisher());
        existing.setUpdateDate(LocalDateTime.now());
    }

    @Override
    public void deleteBookById(UUID id) {
        bookMap.remove(id);
    }

    @Override
    public void patchBookById(UUID id, BookDTO book) {
        BookDTO existing = bookMap.get(id);

        if (StringUtils.hasText(book.getTitle())) {
            existing.setTitle(book.getTitle());
        }
        if (StringUtils.hasText(book.getPublisher())) {
            existing.setPublisher(book.getPublisher());
        }
        if (book.getAuthors() != null) {
            existing.setAuthors(book.getAuthors());
        }
        if (book.getBookGenre() != null) {
            existing.setBookGenre(book.getBookGenre());
        }
        if (book.getIsbn() != null) {
            existing.setIsbn(book.getIsbn());
        }
        if (book.getPrice() != null) {
            existing.setPrice(book.getPrice());
        }
    }
}










