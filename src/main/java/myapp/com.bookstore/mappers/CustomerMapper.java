package myapp.com.bookstore.mappers;

import myapp.com.bookstore.entity.Customer;
import myapp.com.bookstore.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
