package myapp.com.bookstore.controller;

import myapp.com.bookstore.model.BookDTO;
import myapp.com.bookstore.model.BookDTOModelAssembler;
import myapp.com.bookstore.services.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping
public class BookController {

    private final BookService bookService;
    private final BookDTOModelAssembler assembler;
//    private final BookMapper bookMapper;

    public static final String BOOK_PATH = "/api/v1/books";
    public static final String BOOK_PATH_ID = BOOK_PATH + "/{bookId}";


    @GetMapping(BOOK_PATH)
    public ResponseEntity<CollectionModel<EntityModel<BookDTO>>> listBooks() {
        List<EntityModel<BookDTO>> books = bookService.listBooks().stream()
                .map(assembler::toModel)
                .toList();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(CollectionModel.of(books));
        }
    }

    @GetMapping(BOOK_PATH_ID)
    public ResponseEntity<EntityModel<BookDTO>> getBookById(@PathVariable("bookId") UUID bookId) {
        Optional<BookDTO> bookDTO = bookService.getBookById(bookId);
        if (bookDTO.isPresent()) {
            EntityModel<BookDTO> bookEntityModel = assembler.toModel(bookDTO.get());
            return ResponseEntity.ok(bookEntityModel);
        } else {
            throw new NotFoundException("Book not found with id: " + bookId);
        }
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
