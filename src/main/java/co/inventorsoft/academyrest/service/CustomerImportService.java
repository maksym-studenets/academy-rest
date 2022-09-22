package co.inventorsoft.academyrest.service;

import co.inventorsoft.academyrest.domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerImportService {

    public final CustomerService customerService;

    public void importCustomers(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<Customer> customers = new ArrayList<>();
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .forEach(s -> {
                        String[] split = s.split(",");
                        customers.add(
                                new Customer()
                                .setFirstName(split[0])
                                .setLastName(split[1])
                                .setEmail(split[2])
                                .setPhone(split[3])
                        );
                    });
            customerService.create(customers);
            log.info("Customers {}", customers);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
