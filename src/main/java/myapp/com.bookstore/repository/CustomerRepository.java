package myapp.com.bookstore.repository;

import myapp.com.bookstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
