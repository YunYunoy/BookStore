package myapp.com.bookstore.security.entity;

import lombok.Data;

@Data
public class SignUpDTO {
    private String name;
    private String email;
    private String password;
}