package myapp.com.bookstore.services;

import lombok.RequiredArgsConstructor;
import myapp.com.bookstore.entity.Book;
import myapp.com.bookstore.mappers.ModelBookMapper;
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
    private final ModelBookMapper modelBookMapper;

    @Override
    public List<BookDTO> listBooks() {
        return bookRepository.findAll()
                .stream()
                .map(modelBookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(UUID id) {
        return Optional.ofNullable(modelBookMapper.toDTO(bookRepository.findById(id).orElse(null)));
    }

    @Override
    public BookDTO saveNewBook(BookDTO bookDTO) {
        return modelBookMapper.toDTO(bookRepository.save(modelBookMapper.toEntity(bookDTO)));
    }

    @Override
    public Optional<BookDTO> updateBookById(UUID id, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            book.setTitle(bookDTO.getTitle());
            book.setPrice(bookDTO.getPrice());

            Book updatedBook = bookRepository.save(book);
            return Optional.of(modelBookMapper.toDTO(updatedBook));
        }
        return Optional.empty();
    }

    @Override
    public Boolean deleteBookById(UUID id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void patchBookById(UUID id, BookDTO book) {
    }
}
