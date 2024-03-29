package myapp.com.bookstore.services;

import myapp.com.bookstore.model.BookDTO;
import lombok.extern.slf4j.Slf4j;
import myapp.com.bookstore.enums.BookGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final Map<UUID, BookDTO> bookMap;

    public BookServiceImpl() {
        this.bookMap = new HashMap<>();

        BookDTO book1 = BookDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .bookGenre(BookGenre.FANTASY)
                .title("Witcher")
                .authors(new HashSet<>(Arrays.asList("Andrzej Sapkowski", "CD Projekt RED")))
                .publisher("Publisher 1")
                .price(new BigDecimal("20.00"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BookDTO book2 = BookDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .bookGenre(BookGenre.SCIENCE)
                .title("Effective Java")
                .authors(new HashSet<>(List.of("Joshua Bloch")))
                .price(new BigDecimal("30.00"))
                .publisher("Publisher 2")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BookDTO book3 = BookDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .bookGenre(BookGenre.HORROR)
                .title("IT")
                .authors(new HashSet<>(List.of("Stephen King")))
                .publisher("Publisher 3")
                .price(new BigDecimal("30.00"))
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
    public Optional<BookDTO> updateBookById(UUID id, BookDTO book) {
        BookDTO savedBook = bookMap.get(id);
        savedBook.setAuthors(book.getAuthors());
        savedBook.setBookGenre(book.getBookGenre());
        savedBook.setIsbn(book.getIsbn());
        savedBook.setPrice(book.getPrice());
        savedBook.setTitle(book.getTitle());
        savedBook.setPublisher(book.getPublisher());
        savedBook.setUpdateDate(LocalDateTime.now());
        return Optional.of(savedBook);
    }

    @Override
    public Boolean deleteBookById(UUID id) {
        bookMap.remove(id);
        return true;
    }

    @Override
    public void patchBookById(UUID id, BookDTO book) {
        BookDTO savedBook = bookMap.get(id);

        if (StringUtils.hasText(book.getTitle())) {
            savedBook.setTitle(book.getTitle());
        }
        if (StringUtils.hasText(book.getPublisher())) {
            savedBook.setPublisher(book.getPublisher());
        }
        if (book.getAuthors() != null) {
            savedBook.setAuthors(book.getAuthors());
        }
        if (book.getBookGenre() != null) {
            savedBook.setBookGenre(book.getBookGenre());
        }
        if (book.getIsbn() != null) {
            savedBook.setIsbn(book.getIsbn());
        }
        savedBook.setPrice(book.getPrice());
    }

    @Override
    public Page<BookDTO> listBooksPageable(String title, BookGenre bookGenre, Integer pageNumber, Integer pageSize) {
        return new PageImpl<>(new ArrayList<>(bookMap.values()));
    }
}










