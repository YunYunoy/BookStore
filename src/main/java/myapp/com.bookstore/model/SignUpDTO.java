package myapp.com.bookstore.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDTO {

    @Size(min = 4, max = 255, message = "Name must be between 4 and 255 characters")
    @NotNull(message = "Name is required")
    private String name;

    @Size(min = 4, max = 255, message = "Email must be between 4 and 255 characters")
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 4, max = 255, message = "Password must be between 4 and 255 characters")
    @NotNull(message = "Password is required")
    private String password;
}