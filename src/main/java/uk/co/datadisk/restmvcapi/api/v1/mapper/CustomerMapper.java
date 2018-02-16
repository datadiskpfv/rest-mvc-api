package uk.co.datadisk.restmvcapi.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.restmvcapi.api.v1.model.CustomerDTO;
import uk.co.datadisk.restmvcapi.domain.Customer;

@Mapper(componentModel="spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}