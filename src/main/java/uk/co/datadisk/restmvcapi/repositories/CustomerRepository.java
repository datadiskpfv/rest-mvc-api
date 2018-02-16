package uk.co.datadisk.restmvcapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.restmvcapi.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Customer findById(Long id);
}
