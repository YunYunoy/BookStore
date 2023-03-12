package myapp.com.bookstore.services;

import myapp.com.bookstore.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> listCustomers();

    CustomerDTO getCustomerById(UUID id);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Boolean deleteCustomerById(UUID id);

    Optional<CustomerDTO> updateCustomerById(UUID id, CustomerDTO customer);

    void patchCustomer(UUID id, CustomerDTO customer);
}
