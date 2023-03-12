package myapp.com.bookstore.services;

import lombok.RequiredArgsConstructor;
import myapp.com.bookstore.mappers.BookMapper;
import myapp.com.bookstore.model.BookDTO;
import myapp.com.bookstore.repository.BookRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BookServiceJPA implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDTO> listBooks() {
        return null;
//                bookRepository.findAll()
//                .stream()
//                .map(bookMapper::bookToBookDTO)
//                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(UUID id) {
        return Optional.empty();
    }

    @Override
    public BookDTO saveNewBook(BookDTO book) {
        return null;
    }

    @Override
    public Optional<BookDTO> updateBookById(UUID id, BookDTO book) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteBookById(UUID id) {
        return null;
    }

    @Override
    public void patchBookById(UUID id, BookDTO book) {

    }
}
