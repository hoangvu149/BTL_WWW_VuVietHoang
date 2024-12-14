package vn.edu.iuh.fit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.Models.Address;
import vn.edu.iuh.fit.Repositories.AddressRepository;

@Service
public class AdressService {

    @Autowired
    private AddressRepository addressRepository;

    public AdressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    private Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    private Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    private void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    private Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

}
