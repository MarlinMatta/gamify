package edu.uapa.web.app.gamify.services.securities;

import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.repositories.securities.UserRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo repository;

    public UserService(UserRepo repository) {
        this.repository = repository;
    }

    private User merge(User item, String userName) {
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

    public User bootStrap(User item) {
        return merge(item, Constants.SYSTEM_USER);
    }

    public Optional<User> login(String userName, String email, String password) {
        return repository.findByUserNameOrEmailAndPasswordAndEnabled(userName, email, password, true);
    }

    public Optional<User> findByUserNameAndPasswordAndEnabled(String userName, String password, boolean enabled) {
        return repository.findByUserNameAndPasswordAndEnabled(userName, password, enabled);
    }

    public Optional<User> findByEmailAndPasswordAndEnabled(String email, String password, boolean enabled) {
        return repository.findByEmailAndPasswordAndEnabled(email, password, enabled);
    }

    public Page<User> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByUserNameLikeAndEnabled(pageable, filterValue, true);
    }

    public long count(String filterValue) {
        return repository.countByUserNameLikeAndEnabled(filterValue, true);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }
}