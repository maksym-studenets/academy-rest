package co.inventorsoft.academyrest.controller;

import co.inventorsoft.academyrest.domain.dto.CustomerDto;
import co.inventorsoft.academyrest.service.CustomerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getAll(HttpServletRequest request) {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable Integer id) {
        return customerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody @Valid CustomerDto customerDto) {
        return customerService.create(customerDto);
    }

    @PutMapping("/{id}")
    public CustomerDto update(@PathVariable Integer id,
                              @RequestBody CustomerDto customerDto) {
        return customerService.update(id, customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        customerService.delete(id);
    }

    @Getter
    @Setter
    private static class GetRequestParams {
        private String query;
        private Integer size;
        private String sort;
        private String filter;
    }
}
