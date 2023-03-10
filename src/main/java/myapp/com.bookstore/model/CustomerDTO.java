package myapp.com.bookstore.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CustomerDTO {

    private String name;
    private UUID id;
    private Integer version;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}