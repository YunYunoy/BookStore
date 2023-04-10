package myapp.com.bookstore.model;

import myapp.com.bookstore.controller.BookController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookDTOModelAssembler implements RepresentationModelAssembler<BookDTO, EntityModel<BookDTO>> {

    @Override
    public EntityModel<BookDTO> toModel(BookDTO bookDTO) {
        return EntityModel.of(bookDTO,
                linkTo(methodOn(BookController.class).getBookById(bookDTO.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).listBooks()).withRel("books"));
    }
}
