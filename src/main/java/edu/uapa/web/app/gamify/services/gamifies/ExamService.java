package edu.uapa.web.app.gamify.services.gamifies;

import edu.uapa.web.app.gamify.domains.gamifies.Exam;
import edu.uapa.web.app.gamify.repositories.gamifies.ExamRepo;
import edu.uapa.web.app.gamify.services.school.SubjectService;
import edu.uapa.web.app.gamify.services.school.TeacherService;
import edu.uapa.web.app.gamify.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class ExamService {

    private final ExamRepo repository;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final TopicService topicService;

    private Exam merge(Exam item, String userName) {
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

    public Exam bootStrap(Exam item) {
        if (item.getSubject().getId() != null) {
            item.setSubject(subjectService.bootStrap(item.getSubject()));
        }
        if (item.getTopic().getId() != null) {
            item.setTopic(topicService.bootStrap(item.getTopic()));
        }
        return merge(item, Constants.SYSTEM_USER);
    }

    public Page<Exam> findAll(Pageable pageable, String filterValue) {
        return repository.findAllBySubject_NameLikeAndEnabled(pageable, filterValue, true);
    }

    public List<Exam> findAll() {
        return repository.findAll();
    }

    public long count(String filterValue) {
        return repository.countBySubject_NameLikeAndEnabled(filterValue, true);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(item -> {
            item.setEnabled(false);
            bootStrap(item);
        });
    }
}