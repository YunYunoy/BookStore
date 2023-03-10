package myapp.com.bookstore.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class BookDTO {
    private UUID id;
    private Integer version;
    private Long isbn;
    private String title;
    private BigDecimal price;
    public BookGenre bookGenre;
    private List<String> authors;
    private String publisher;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    public enum BookGenre {
        FICTION, DRAMA, ROMANCE, SCIENCE_FICTION, FANTASY, HORROR, DOCUMENTARY, PSYCHOLOGICAL, POETRY,SCIENCE;
    }
}

