package myapp.com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import myapp.com.bookstore.enums.BookGenre;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false, length = 36, columnDefinition = "varchar")
    private UUID id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "book_genre")
    @Enumerated(EnumType.STRING)
    public BookGenre bookGenre;

    @ElementCollection
    @Column(name = "authors")
    private Set<String> authors = new HashSet<>();

    @Column(name = "publisher")
    private String publisher;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

}
