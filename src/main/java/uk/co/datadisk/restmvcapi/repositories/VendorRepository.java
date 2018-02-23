package uk.co.datadisk.restmvcapi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.restmvcapi.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
