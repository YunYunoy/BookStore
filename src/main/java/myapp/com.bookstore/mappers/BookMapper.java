package myapp.com.bookstore.mappers;

import myapp.com.bookstore.model.BookDTO;
import org.mapstruct.Mapper;

import java.awt.print.Book;

@Mapper
public interface BookMapper {

    BookDTO BookToBookDTO(Book book);

    Book BookDTOToBook(BookDTO bookDTO);
}
