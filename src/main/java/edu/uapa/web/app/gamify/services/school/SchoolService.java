package edu.uapa.web.app.gamify.services.school;

import edu.uapa.web.app.gamify.domains.schools.School;
import edu.uapa.web.app.gamify.repositories.school.SchoolRepo;
import edu.uapa.web.app.gamify.services.address.AddressService;
import edu.uapa.web.app.gamify.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SchoolService {

    private final SchoolRepo repository;
    private final AddressService addressService;

    private School merge(School item, String userName) {
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

    public School bootStrap(School item) {
        if (item.getAddress().getId() != null) {
            item.setAddress(addressService.bootStrap(item.getAddress()));
        }
        return merge(item, Constants.SYSTEM_USER);
    }

    public Optional<School> findByDistrict(String district) {
        return repository.findByDistrictAndEnabled(district, true);
    }

    public Page<School> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByNameLikeAndEnabled(pageable, filterValue, true);
    }

    public List<School> findAll() {
        return repository.findAll();
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