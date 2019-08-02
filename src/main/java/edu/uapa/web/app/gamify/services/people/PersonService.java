package edu.uapa.web.app.gamify.services.people;

import edu.uapa.web.app.gamify.domains.people.Person;
import edu.uapa.web.app.gamify.repositories.people.PersonRepo;
import edu.uapa.web.app.gamify.services.address.AddressService;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonService {

    private final PersonRepo repository;
    private final AddressService addressService;

    public PersonService(PersonRepo repository, AddressService addressService) {
        this.repository = repository;
        this.addressService = addressService;
    }

    private Person merge(Person item, String userName) {
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

    public Person bootStrap(Person item) {
        if (item.getAddress().getId() != null) {
            item.getAddress().setSector(item.getAddress().getCity());
            item.setAddress(addressService.bootStrap(item.getAddress()));
        }
        return merge(item, Constants.SYSTEM_USER);
    }}