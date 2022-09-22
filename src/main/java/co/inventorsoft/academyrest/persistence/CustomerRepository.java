package co.inventorsoft.academyrest.persistence;

import co.inventorsoft.academyrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
