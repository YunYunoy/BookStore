package myapp.com.bookstore.bootstrap;

import lombok.RequiredArgsConstructor;
import myapp.com.bookstore.entity.Author;
import myapp.com.bookstore.entity.Book;
import myapp.com.bookstore.repository.AuthorRepository;
import myapp.com.bookstore.repository.BookRepository;
import myapp.com.bookstore.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {

        Author Schildt = new Author("Herbert", "Schildt");
        Book JFB = new Book("123 123 123", "Java for beginners", new BigDecimal(56.99));
        Schildt.getBooks().add(JFB);
        JFB.getAuthors().add(Schildt);
        authorRepository.save(Schildt);
        bookRepository.save(JFB);

        Author Bloch = new Author("Joshua", "Bloch");
        Book JEP = new Book("321 321 321", "Java effective programming", new BigDecimal(42.99));
        Bloch.getBooks().add(JEP);
        JEP.getAuthors().add(Bloch);
        authorRepository.save(Bloch);
        bookRepository.save(JEP);

        System.out.println("Books: "+bookRepository.count());
        System.out.println("Authors: "+authorRepository.count());
    }
}
