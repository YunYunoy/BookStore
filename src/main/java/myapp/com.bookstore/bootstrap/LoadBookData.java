package myapp.com.bookstore.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.com.bookstore.mappers.BookMapper;
import myapp.com.bookstore.model.BookDTO;
import myapp.com.bookstore.enums.BookGenre;
import myapp.com.bookstore.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoadBookData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public void run(String... args) throws Exception {

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.FANTASY)
                .title("Witcher")
                .authors(new HashSet<>(Arrays.asList("Andrzej Sapkowski", "CD Projekt RED")))
                .publisher("Slavic Mythology Institution")
                .price(new BigDecimal("30.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.FANTASY)
                .title("Harry Potter and the Sorcerer's Stone")
                .authors(new HashSet<>(Arrays.asList("J.K. Rowling")))
                .publisher("Bloomsbury Publishing")
                .price(new BigDecimal("24.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.FANTASY)
                .title("The Hobbit")
                .authors(new HashSet<>(Arrays.asList("J.R.R. Tolkien")))
                .publisher("George Allen & Unwin")
                .price(new BigDecimal("19.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.FANTASY)
                .title("A Game of Thrones")
                .authors(new HashSet<>(Arrays.asList("George R.R. Martin")))
                .publisher("Bantam Spectra")
                .price(new BigDecimal("29.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.FANTASY)
                .title("The Name of the Wind")
                .authors(new HashSet<>(Arrays.asList("Patrick Rothfuss")))
                .publisher("DAW Books")
                .price(new BigDecimal("26.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.FANTASY)
                .title("The Chronicles of Narnia")
                .authors(new HashSet<>(Arrays.asList("C.S. Lewis")))
                .publisher("Geoffrey Bles")
                .price(new BigDecimal("22.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.ROMANCE)
                .title("The Notebook")
                .authors(new HashSet<>(List.of("Nicholas Sparks")))
                .publisher("Hachette Book Group")
                .price(new BigDecimal("15.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.MYSTERY)
                .title("The Da Vinci Code")
                .authors(new HashSet<>(List.of("Dan Brown")))
                .publisher("Doubleday")
                .price(new BigDecimal("20.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.HORROR)
                .title("It")
                .authors(new HashSet<>(List.of("Stephen King")))
                .publisher("Viking Press")
                .price(new BigDecimal("25.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.SCIENCE_FICTION)
                .title("Dune")
                .authors(new HashSet<>(List.of("Frank Herbert")))
                .publisher("Chilton Books")
                .price(new BigDecimal("22.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.BIOGRAPHY)
                .title("Steve Jobs")
                .authors(new HashSet<>(List.of("Walter Isaacson")))
                .publisher("Simon & Schuster")
                .price(new BigDecimal("18.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.HISTORICAL_FICTION)
                .title("The Pillars of the Earth")
                .authors(new HashSet<>(List.of("Ken Follett")))
                .publisher("William Morrow and Company")
                .price(new BigDecimal("26.99"))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .bookGenre(BookGenre.THRILLER)
                .title("The Girl on the Train")
                .authors(new HashSet<>(List.of("Paula Hawkins")))
                .publisher("Riverhead Books")
                .price(new BigDecimal("17.99"))
                .build()));

        log.debug("Books Loaded: " + bookRepository.count());
    }
}
