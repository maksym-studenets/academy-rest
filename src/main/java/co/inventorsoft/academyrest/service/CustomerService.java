package co.inventorsoft.academyrest.service;

import co.inventorsoft.academyrest.domain.AbstractIdentifiable;
import co.inventorsoft.academyrest.domain.Customer;
import co.inventorsoft.academyrest.domain.dto.CustomerDto;
import co.inventorsoft.academyrest.domain.mapper.CustomerMapper;
import co.inventorsoft.academyrest.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDto> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public CustomerDto getById(Integer id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found!"));
    }

    public List<CustomerDto> search(String query) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Customer> example = Example.of(
                new Customer()
                        .setFirstName(query)
                        .setLastName(query)
                        .setEmail(query)
                        .setPhone(query),
                exampleMatcher
        );

        return customerRepository.findAll(example)
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public Integer create(CustomerDto customerDto) {
        return customerRepository.save(customerMapper.toEntity(customerDto))
                .getId();
    }

    public List<Integer> create(List<Customer> customers) {
        return customerRepository.saveAll(customers)
                .stream()
                .map(AbstractIdentifiable::getId)
                .toList();
    }

    public CustomerDto update(Integer id, CustomerDto dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found!"));
        customerMapper.update(dto, customer);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
