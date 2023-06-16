package myapp.com.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import myapp.com.bookstore.enums.BookGenre;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class BookDTO {

    private UUID id;
    private Integer version;

    @ISBN(message = "Invalid ISBN format.")
    private String isbn;

    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters.")
    @NotNull(message = "Title is required.")
    private String title;

    @Positive(message = "Price must be a positive value.")
    @NotNull(message = "Price is required.")
    private BigDecimal price;

    private BookGenre bookGenre;

    @ElementCollection
    @CollectionTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"))
    private Set<String> authors;

    @Size(min = 2, max = 255, message = "Publisher must be between 2 and 255 characters.")
    private String publisher;

    @JsonIgnore
    private LocalDateTime createdDate;
    @JsonIgnore
    private LocalDateTime updateDate;

}

