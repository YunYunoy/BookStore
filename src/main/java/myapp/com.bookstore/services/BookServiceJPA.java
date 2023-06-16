package myapp.com.bookstore.services;

import lombok.RequiredArgsConstructor;
import myapp.com.bookstore.entity.Book;
import myapp.com.bookstore.mappers.BookMapper;
import myapp.com.bookstore.model.BookDTO;
import myapp.com.bookstore.enums.BookGenre;
import myapp.com.bookstore.repository.BookRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Override
    public List<BookDTO> listBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::bookToBookDTO)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<BookDTO> getBookById(UUID id) {
        return Optional.ofNullable(bookMapper.bookToBookDTO(bookRepository.findById(id).orElse(null)));
    }

    @Override
    public BookDTO saveNewBook(BookDTO bookDTO) {
        return bookMapper.bookToBookDTO(bookRepository.save(bookMapper.bookDTOToBook(bookDTO)));
    }

    @Override
    public Optional<BookDTO> updateBookById(UUID id, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDTO.getTitle());
            book.setPrice(bookDTO.getPrice());

            Book updatedBook = bookRepository.save(book);
            return Optional.of(bookMapper.bookToBookDTO(updatedBook));
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

    public PageRequest PageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > 1000) {
                queryPageSize = 1000;
            } else {
                queryPageSize = pageSize;
            }
        }
        Sort sort = Sort.by(Sort.Order.asc("title"));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }

    @Override
    public Page<BookDTO> listBooksPageable(String title, BookGenre bookGenre, Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = PageRequest(pageNumber, pageSize);

        Page<Book> bookPage;

        if (StringUtils.hasText(title) && bookGenre == null) {
            bookPage = bookRepository.findAllByTitleIsLikeIgnoreCase(title, pageRequest);
        } else if (!StringUtils.hasText(title) && bookGenre != null) {
            bookPage = bookRepository.findAllByBookGenre(bookGenre, pageRequest);
        } else if (StringUtils.hasText(title) && bookGenre != null) {
            bookPage = bookRepository.findAllByTitleIsLikeIgnoreCaseAndBookGenre(title, bookGenre, pageRequest);
        } else {
            bookPage = bookRepository.findAll(pageRequest);
        }

        return bookPage.map(bookMapper::bookToBookDTO);
    }

}
















