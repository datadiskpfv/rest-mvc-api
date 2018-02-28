package uk.co.datadisk.restmvcapi.services;


import org.springframework.stereotype.Service;
import uk.co.datadisk.restmvcapi.Exceptions.ResourceNotFoundException;
import uk.co.datadisk.restmvcapi.api.v1.mapper.VendorMapper;
import uk.co.datadisk.restmvcapi.api.v1.model.VendorDTO;
import uk.co.datadisk.restmvcapi.api.v1.model.VendorListDTO;
import uk.co.datadisk.restmvcapi.controllers.v1.VendorController;
import uk.co.datadisk.restmvcapi.domain.Vendor;
import uk.co.datadisk.restmvcapi.repositories.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jt on 10/6/17.
 */
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        Vendor vendor = vendorRepository.findOne(id);

        if(vendor == null)
            throw new ResourceNotFoundException();

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
        return vendorDTO;
    }

    @Override
    public VendorListDTO getAllVendors() {
        List<VendorDTO> vendorDTOS = vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());

        return new VendorListDTO(vendorDTOS);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOtoVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

        Vendor vendorToSave = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendorToSave.setId(id);

        return saveAndReturnDTO(vendorToSave);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {

        Vendor vendor = vendorRepository.findOne(id);

        if(vendor == null)
            throw new ResourceNotFoundException();

        if(vendorDTO.getName() != null){
            vendor.setName(vendorDTO.getName());
        }

        return saveAndReturnDTO(vendor);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.delete(id);
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);

        returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDto;
    }
}
