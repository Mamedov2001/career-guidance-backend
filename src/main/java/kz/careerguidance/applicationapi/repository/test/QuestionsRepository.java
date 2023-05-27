package kz.careerguidance.applicationapi.repository.test;


import kz.careerguidance.applicationapi.entity.test.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestion(String name);
}
