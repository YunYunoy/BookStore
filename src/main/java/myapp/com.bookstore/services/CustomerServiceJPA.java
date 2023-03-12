package myapp.com.bookstore.services;

import lombok.RequiredArgsConstructor;
import myapp.com.bookstore.entity.Customer;
import myapp.com.bookstore.mappers.ModelCustomerMapper;
import myapp.com.bookstore.model.CustomerDTO;
import myapp.com.bookstore.repository.CustomerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelCustomerMapper modelCustomerMapper;

    @Override
    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(modelCustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(modelCustomerMapper.toDTO(customerRepository.findById(id).orElse(null)));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
        return modelCustomerMapper.toDTO(customerRepository.save(modelCustomerMapper.toEntity(customerDTO)));
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID id, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());

            Customer updatedCustomer = customerRepository.save(customer);
            return Optional.of(modelCustomerMapper.toDTO(updatedCustomer));
        }
        return Optional.empty();
    }

    @Override
    public void patchCustomer(UUID id, CustomerDTO customer) {
    }

    @Override
    public Boolean deleteCustomerById(UUID id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
