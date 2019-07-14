package edu.uapa.web.app.gamify.services.address;

import edu.uapa.web.app.gamify.domains.locations.Address;
import edu.uapa.web.app.gamify.repositories.address.AddressRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
class AddressService {

    private final AddressRepo repository;

    public AddressService(AddressRepo repository) {
        this.repository = repository;
    }

    private Address merge(Address item, String userName) {
        if (item != null) {
            if (repository.existsById(item.getId())) {
                item.setLastModifiedDate(new Date());
                item.setLastModifiedBy(userName);
            } else {
                item.setCreatedBy(userName);
                item.setCreatedDate(new Date());
            }
            return repository.save(item);
        }
        return null;
    }

    Address bootStrap(Address item) {
        return merge(item, Constants.SYSTEM_USER);
    }
}