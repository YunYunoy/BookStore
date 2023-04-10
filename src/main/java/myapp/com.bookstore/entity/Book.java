package myapp.com.bookstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import myapp.com.bookstore.model.BookDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

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
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false, length = 36, columnDefinition = "varchar")
    private UUID id;

    @Version
    private Integer version;
    private String isbn;


    @Length(max = 255)
    @NotBlank
    @NotNull
    private String title;

    @PositiveOrZero
    @NotNull
    private BigDecimal price;

    @Enumerated
    public BookDTO.BookGenre bookGenre;

    @ElementCollection
    private Set<String> authors = new HashSet<>();


    private String publisher;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

}
