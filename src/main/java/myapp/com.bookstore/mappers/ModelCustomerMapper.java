package myapp.com.bookstore.mappers;

import myapp.com.bookstore.entity.Customer;
import myapp.com.bookstore.model.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ModelCustomerMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public CustomerDTO toDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

}
