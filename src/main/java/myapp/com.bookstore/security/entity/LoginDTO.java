package myapp.com.bookstore.security.entity;

import lombok.Data;

@Data
public class LoginDTO {
    private String usernameOrEmail;
    private String password;
}
