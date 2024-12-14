package vn.edu.iuh.fit.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.Models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}