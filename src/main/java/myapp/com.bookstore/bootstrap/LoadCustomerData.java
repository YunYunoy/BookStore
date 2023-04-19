package myapp.com.bookstore.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.com.bookstore.mappers.CustomerMapper;
import myapp.com.bookstore.model.CustomerDTO;
import myapp.com.bookstore.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoadCustomerData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void run(String... args) throws Exception {

        customerRepository.save(customerMapper.customerDTOToCustomer(CustomerDTO.builder()
                .version(1)
                .name("Goblin")
                .email("goblin@gmail.com")
                .build()));

        customerRepository.save(customerMapper.customerDTOToCustomer(CustomerDTO.builder()
                .version(1)
                .name("Neuron")
                .email("neuron@gmail.com")
                .build()));

        log.debug("Customers Loaded: " + customerRepository.count());
    }
}
