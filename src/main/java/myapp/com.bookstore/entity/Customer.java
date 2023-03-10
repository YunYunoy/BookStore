package myapp.com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false,nullable = false)
    private UUID id;
    private String name;
    @Version
    private Integer version;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
