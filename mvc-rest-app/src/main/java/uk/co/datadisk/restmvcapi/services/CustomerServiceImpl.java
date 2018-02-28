package uk.co.datadisk.restmvcapi.services;

import org.springframework.stereotype.Service;
import uk.co.datadisk.restmvcapi.Exceptions.ResourceNotFoundException;
import uk.co.datadisk.restmvcapi.api.v1.mapper.CustomerMapper;
import uk.co.datadisk.model.CustomerDTO;
import uk.co.datadisk.restmvcapi.controllers.v1.CustomerController;
import uk.co.datadisk.restmvcapi.domain.Customer;
import uk.co.datadisk.restmvcapi.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                   CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                   customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                   return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new));

        //if(customer == null)
        //    throw new ResourceNotFoundException();

        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customer.get());
        returnDto.setCustomerUrl(CustomerController.BASE_URL + "/" + id);

        return returnDto;
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);

        System.out.println("Customer Service createNewCustomer: " + customer.getFirstname());

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

        returnDto.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());

        return returnDto;
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

        returnDto.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());

        return returnDto;
    }


    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {

        Customer customer = customerRepository.findOne(id);

        if(customerDTO.getFirstname() != null){
            customer.setFirstname(customerDTO.getFirstname());
        }

        if(customerDTO.getLastname() != null){
            customer.setLastname(customerDTO.getLastname());
        }

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

        returnDto.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());

        return returnDto;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.delete(id);
    }
}
