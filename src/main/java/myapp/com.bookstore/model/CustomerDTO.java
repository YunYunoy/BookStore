package myapp.com.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CustomerDTO {

    private UUID id;

    @Size(min = 3,max = 255 ,message = "Name must be between 3 and 255 characters.")
    @NotNull(message = "Name is required.")
    private String name;

    private Integer version;

    @Email(message = "Invalid email format.")
    private String email;

    @JsonIgnore
    private LocalDateTime createdDate;

    @JsonIgnore
    private LocalDateTime updateDate;
}