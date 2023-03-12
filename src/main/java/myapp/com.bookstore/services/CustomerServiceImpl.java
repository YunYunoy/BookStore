package myapp.com.bookstore.services;

import myapp.com.bookstore.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        customerMap = new HashMap<>();

        CustomerDTO Kamcia = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Kamcia")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDTO Goblin = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Goblin")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDTO Neuron = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Neuron")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap.put(Kamcia.getId(), Kamcia);
        customerMap.put(Goblin.getId(), Goblin);
        customerMap.put(Neuron.getId(), Neuron);
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMap.get(id));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name(customer.getName())
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;

    }

    @Override
    public Boolean deleteCustomerById(UUID id) {
        customerMap.remove(id);
        return true;
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID id, CustomerDTO customer) {
        CustomerDTO savedCustomer = customerMap.get(id);
        savedCustomer.setName(customer.getName());
        savedCustomer.setUpdateDate(LocalDateTime.now());
        return Optional.of(savedCustomer);
    }

    @Override
    public void patchCustomer(UUID id, CustomerDTO customer) {
        CustomerDTO savedCustomer = customerMap.get(id);
        if (StringUtils.hasText(customer.getName())) {
            savedCustomer.setName(customer.getName());
        }
    }
}
