package myapp.com.bookstore.repository;

import myapp.com.bookstore.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;


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