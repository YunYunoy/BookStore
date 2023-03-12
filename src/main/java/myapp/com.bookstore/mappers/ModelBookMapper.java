package myapp.com.bookstore.mappers;

import myapp.com.bookstore.entity.Book;
import myapp.com.bookstore.model.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ModelBookMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public BookDTO toDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book toEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }
}
