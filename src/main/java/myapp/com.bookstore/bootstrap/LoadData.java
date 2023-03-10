package myapp.com.bookstore.bootstrap;

import lombok.RequiredArgsConstructor;
import myapp.com.bookstore.repository.AuthorRepository;
import myapp.com.bookstore.repository.BookRepository;
import myapp.com.bookstore.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
