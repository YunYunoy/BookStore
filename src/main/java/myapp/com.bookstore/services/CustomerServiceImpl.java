package myapp.com.bookstore.services;

import myapp.com.bookstore.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customerMap;

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
    public CustomerDTO getCustomerById(UUID id) {
        return customerMap.get(id);
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
    public void deleteCustomerById(UUID id) {
        customerMap.remove(id);
    }

    @Override
    public void updateCustomerById(UUID id, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(id);
        existing.setName(customer.getName());
        existing.setUpdateDate(LocalDateTime.now());
    }

    @Override
    public void patchCustomer(UUID id, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(id);
        if (StringUtils.hasText(customer.getName())) {
            existing.setName(customer.getName());
        }
    }
}
