package myapp.com.bookstore.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class BookDTO {

    private UUID id;
    private Integer version;
    private String isbn;

    @NotNull
    @NotBlank
    private String title;

    private BigDecimal price;
    public BookGenre bookGenre;
    private Set<String> authors;
    private String publisher;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

}

