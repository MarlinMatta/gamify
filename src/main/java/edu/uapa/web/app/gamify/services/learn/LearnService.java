package edu.uapa.web.app.gamify.services.learn;

import edu.uapa.web.app.gamify.domains.learn.Learn;
import edu.uapa.web.app.gamify.repositories.learn.LearnRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LearnService {

    private final LearnRepo repository;

    private Learn merge(Learn item, String userName) {
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

    public Learn bootStrap(Learn item) {
        return merge(item, Constants.SYSTEM_USER);
    }

    public Optional<Learn> findByTopic(long topicId) {
        return repository.findByTopic_IdAndEnabled(topicId, true);
    }

    public List<Learn> findAll() {
        return repository.findAll();
    }

    public long count(long topicId) {
        return repository.countByTopic_IdAndEnabled(topicId, true);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }
}