package uk.co.datadisk.restmvcapi.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.restmvcapi.api.v1.model.VendorDTO;
import uk.co.datadisk.restmvcapi.domain.Vendor;


@Mapper(componentModel="spring")
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOtoVendor(VendorDTO vendorDTO);
}
