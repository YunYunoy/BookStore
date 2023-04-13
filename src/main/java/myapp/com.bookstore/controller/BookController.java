package myapp.com.bookstore.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.com.bookstore.model.BookDTO;
import myapp.com.bookstore.model.BookGenre;
import myapp.com.bookstore.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@AllArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    public static final String BOOK_PATH = "/api/v1/books";
    public static final String BOOK_PATH_ID = BOOK_PATH + "/{bookId}";


    @GetMapping(BOOK_PATH)
    @PreAuthorize("hasAuthority('USER')")
    public List<BookDTO> listBooks() {
        return bookService.listBooks();
    }

    @GetMapping(BOOK_PATH + "/search")
    @PreAuthorize("hasAuthority('USER')")
    public Page<BookDTO> listBooksPageable(@RequestParam(required = false) String title,
                                           @RequestParam(required = false) BookGenre bookGenre,
                                           @RequestParam(required = false) Integer pageNumber,
                                           @RequestParam(required = false) Integer pageSize) {
        return bookService.listBooksPageable(title, bookGenre, pageNumber, pageSize);
    }


    @GetMapping(BOOK_PATH_ID)
    @PreAuthorize("hasAuthority('USER')")
    public BookDTO getBookById(@PathVariable("bookId") UUID bookId) {
        return bookService.getBookById(bookId)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping(BOOK_PATH)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity handlePost(@Validated @RequestBody BookDTO book) {
        BookDTO savedBook = bookService.saveNewBook(book);
        return new ResponseEntity(savedBook, HttpStatus.CREATED);
    }

    @PutMapping(BOOK_PATH_ID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity updateById(@PathVariable("bookId") UUID bookId, @Validated @RequestBody BookDTO book) {
        bookService.updateBookById(bookId, book);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BOOK_PATH_ID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteById(@PathVariable("bookId") UUID bookId) {
        bookService.deleteBookById(bookId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BOOK_PATH_ID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity updateBookPatchById(@PathVariable("bookId") UUID bookId, @Validated @RequestBody BookDTO book) {
        bookService.patchBookById(bookId, book);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}