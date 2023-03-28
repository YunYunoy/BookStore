package myapp.com.bookstore.bootstrap;

import lombok.RequiredArgsConstructor;
import myapp.com.bookstore.mappers.BookMapper;
import myapp.com.bookstore.model.BookDTO;
import myapp.com.bookstore.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override

    public void run(String... args) throws Exception {

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .version(1)
                .bookGenre(BookDTO.BookGenre.FANTASY)
                .title("Witcher")
                .authors(new HashSet<>(Arrays.asList("Andrzej Sapkowski", "CD Projekt RED")))
                .publisher("Slavic Mythology Institution")
                .price(new BigDecimal(30.99))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .version(1)
                .bookGenre(BookDTO.BookGenre.ROMANCE)
                .title("The Notebook")
                .authors(new HashSet<>(Arrays.asList("Nicholas Sparks")))
                .publisher("Hachette Book Group")
                .price(new BigDecimal(15.99))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .version(1)
                .bookGenre(BookDTO.BookGenre.MYSTERY)
                .title("The Da Vinci Code")
                .authors(new HashSet<>(Arrays.asList("Dan Brown")))
                .publisher("Doubleday")
                .price(new BigDecimal(20.99))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .version(1)
                .bookGenre(BookDTO.BookGenre.HORROR)
                .title("It")
                .authors(new HashSet<>(Arrays.asList("Stephen King")))
                .publisher("Viking Press")
                .price(new BigDecimal(25.99))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .version(1)
                .bookGenre(BookDTO.BookGenre.SCIENCE_FICTION)
                .title("Dune")
                .authors(new HashSet<>(Arrays.asList("Frank Herbert")))
                .publisher("Chilton Books")
                .price(new BigDecimal(22.99))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .version(1)
                .bookGenre(BookDTO.BookGenre.BIOGRAPHY)
                .title("Steve Jobs")
                .authors(new HashSet<>(Arrays.asList("Walter Isaacson")))
                .publisher("Simon & Schuster")
                .price(new BigDecimal(18.99))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .version(1)
                .bookGenre(BookDTO.BookGenre.HISTORICAL_FICTION)
                .title("The Pillars of the Earth")
                .authors(new HashSet<>(Arrays.asList("Ken Follett")))
                .publisher("William Morrow and Company")
                .price(new BigDecimal(26.99))
                .build()));

        bookRepository.save(bookMapper.bookDTOToBook(BookDTO.builder()
                .version(1)
                .bookGenre(BookDTO.BookGenre.THRILLER)
                .title("The Girl on the Train")
                .authors(new HashSet<>(Arrays.asList("Paula Hawkins")))
                .publisher("Riverhead Books")
                .price(new BigDecimal(17.99))
                .build()));

        System.out.println("Books: " + bookRepository.count());
    }
}
