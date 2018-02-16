package uk.co.datadisk.restmvcapi.services;

import uk.co.datadisk.restmvcapi.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);
}
