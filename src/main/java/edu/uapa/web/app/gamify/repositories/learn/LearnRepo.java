package edu.uapa.web.app.gamify.repositories.learn;

import edu.uapa.web.app.gamify.domains.learn.Learn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearnRepo extends JpaRepository<Learn, Long> {
    Optional<Learn> findByTopic_IdAndEnabled(long topicId, boolean enabled);

    long countByTopic_IdAndEnabled(long topicId, boolean enabled);
}
