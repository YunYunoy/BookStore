package myapp.com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import myapp.com.bookstore.model.BookDTO;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false, length = 36, columnDefinition = "varchar")
    private UUID id;
    private Integer version;
    private Long isbn;
    private String title;
    private BigDecimal price;
    public BookDTO.BookGenre bookGenre;

    @ManyToMany
    private List<Author> authors;
    private String publisher;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
