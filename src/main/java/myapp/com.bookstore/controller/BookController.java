package myapp.com.bookstore.controller;

import myapp.com.bookstore.mappers.BookMapper;
import myapp.com.bookstore.model.BookDTO;
import myapp.com.bookstore.model.BookDTOModelAssembler;
import myapp.com.bookstore.model.BookGenre;
import myapp.com.bookstore.services.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping
public class BookController {

    private final BookService bookService;

    public static final String BOOK_PATH = "/api/v1/books";
    public static final String BOOK_PATH_ID = BOOK_PATH + "/{bookId}";


    @GetMapping(BOOK_PATH)
    public List<BookDTO> listBooks() {
        return bookService.listBooks();
    }

    @GetMapping(BOOK_PATH + "/search")
    public Page<BookDTO> listBooksPageable(@RequestParam(required = false) String title,
                                           @RequestParam(required = false) BookGenre bookGenre,
                                           @RequestParam(required = false) Integer pageNumber,
                                           @RequestParam(required = false) Integer pageSize) {
        return bookService.listBooksPageable(title, bookGenre, pageNumber, pageSize);
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
    public ResponseEntity updateById(@PathVariable("bookId") UUID bookId, @Validated @RequestBody BookDTO book) {

        bookService.updateBookById(bookId, book);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BOOK_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("bookId") UUID bookId) {

        bookService.deleteBookById(bookId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BOOK_PATH_ID)
    public ResponseEntity updateBookPatchById(@PathVariable("bookId") UUID bookId, @Validated @RequestBody BookDTO book) {

        bookService.patchBookById(bookId, book);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}