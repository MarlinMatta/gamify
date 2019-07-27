package edu.uapa.web.app.gamify.services.school;

import edu.uapa.web.app.gamify.domains.schools.Topic;
import edu.uapa.web.app.gamify.repositories.school.TopicRepo;
import edu.uapa.web.app.gamify.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopicService {

    private final TopicRepo repository;
    private final SubjectService subjectService;

    public TopicService(TopicRepo repository, SubjectService subjectService) {
        this.repository = repository;
        this.subjectService = subjectService;
    }

    private Topic merge(Topic item, String userName) {
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

    public Topic bootStrap(Topic item) {
        if (item.getSubject().getId() != null) {
            item.setSubject(subjectService.bootStrap(item.getSubject()));
        }
        return merge(item, Constants.SYSTEM_USER);
    }

    public Page<Topic> findAll(Pageable pageable, String filterValue) {
        return repository.findAllByNameLikeAndEnabled(pageable, filterValue, true);
    }

    public List<Topic> findAll() {
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