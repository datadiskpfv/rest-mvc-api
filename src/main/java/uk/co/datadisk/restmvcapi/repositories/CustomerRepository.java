package uk.co.datadisk.restmvcapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.restmvcapi.domain.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    //List<Customer> findById(Long id);
}
