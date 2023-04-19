package myapp.com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
public class BookOrder {

    public BookOrder(UUID id, Long version, Customer customer, Timestamp createdCate, Timestamp updatedDate) {
        this.id = id;
        this.version = version;
        this.setCustomer(customer);
        this.createdCate = createdCate;
        this.updatedDate = updatedDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    private UUID id;
    @Version
    private Long version;

    @ManyToOne
    private Customer customer;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdCate;
    @UpdateTimestamp
    private Timestamp updatedDate;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.getBeerOrders().add(this);
    }
}
