package myapp.com.bookstore.repository;

import myapp.com.bookstore.entity.Book;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.lang.reflect.Array;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testSaveBook() {

        Book book = bookRepository.save(Book.builder()
                .title("test title")
                .price(new BigDecimal("20"))
                .build());

        bookRepository.flush();
        assertThat(book).isNotNull();
        assertThat(book.getId()).isNotNull();
    }
}