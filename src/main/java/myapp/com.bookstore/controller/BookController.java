package myapp.com.bookstore.controller;

import myapp.com.bookstore.model.BookDTO;
import myapp.com.bookstore.services.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping
public class BookController {
    private final BookService bookService;

    public static final String BOOK_PATH = "/api/v1/book";
    public static final String BOOK_PATH_ID = BOOK_PATH + "/{bookId}";


    @GetMapping(BOOK_PATH)
    public List<BookDTO> listBooks() {
        return bookService.listBooks();
    }

    @GetMapping(BOOK_PATH_ID)
    public BookDTO getBookById(@PathVariable("bookId") UUID bookId) {
        return bookService.getBookById(bookId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(BOOK_PATH)
    public ResponseEntity handlePost(@Validated @RequestBody BookDTO book) {

        BookDTO savedBook = bookService.saveNewBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BOOK_PATH + "/" + savedBook.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(BOOK_PATH_ID)
    public ResponseEntity updateById(@PathVariable("bookId") UUID bookId,@Validated @RequestBody BookDTO book) {

        bookService.updateBookById(bookId, book);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BOOK_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("bookId") UUID bookId) {

        bookService.deleteBookById(bookId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BOOK_PATH_ID)
    public ResponseEntity updateBookPatchById(@PathVariable("bookId") UUID bookId,@Validated @RequestBody BookDTO book) {

        bookService.patchBookById(bookId, book);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
