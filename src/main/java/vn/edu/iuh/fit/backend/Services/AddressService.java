package vn.edu.iuh.fit.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.Models.Address;
import vn.edu.iuh.fit.backend.Repositories.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

}
