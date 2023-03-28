package myapp.com.bookstore.mappers;

import myapp.com.bookstore.entity.Book;
import myapp.com.bookstore.model.BookDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    Book bookDTOToBook(BookDTO bookDTO);

    BookDTO bookToBookDTO(Book book);
}
