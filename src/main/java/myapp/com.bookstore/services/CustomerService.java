package myapp.com.bookstore.services;

import myapp.com.bookstore.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> listCustomers();

    CustomerDTO getCustomerById(UUID id);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    void deleteCustomerById(UUID id);

    void updateCustomerById(UUID id, CustomerDTO customer);

    void patchCustomer(UUID id, CustomerDTO customer);
}
