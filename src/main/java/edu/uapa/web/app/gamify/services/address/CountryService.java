package edu.uapa.web.app.gamify.services.address;

import edu.uapa.web.app.gamify.domains.locations.Country;
import edu.uapa.web.app.gamify.repositories.address.CountryRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepo repository;

    public CountryService(CountryRepo repository) {
        this.repository = repository;
    }

    private Country merge(Country item, String userName) {
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

    public Country bootStrap(Country item) {
        return merge(item, Constants.SYSTEM_USER);
    }

    public Page<Country> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByNameLikeAndEnabled(pageable, filterValue, true);
    }

    public List<Country> findAll() {
        return repository.findAll();
    }

    public Optional<Country> findByName(String name) {
        return repository.findByNameAndEnabled(name, true);
    }


    public long count(String filterValue) {
        return repository.countByNameLikeAndEnabled(filterValue, true);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }
}