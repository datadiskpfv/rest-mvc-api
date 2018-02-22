package uk.co.datadisk.restmvcapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.restmvcapi.domain.Customer;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findById(Long id);
}
