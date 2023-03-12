package myapp.com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import myapp.com.bookstore.model.BookDTO;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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
    private String isbn;
    private String title;
    private BigDecimal price;
    public BookDTO.BookGenre bookGenre;

    @ManyToMany
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"),inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();
    private String publisher;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    public Book(String isbn, String title, BigDecimal price) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }
}
