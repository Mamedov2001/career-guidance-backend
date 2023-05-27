package kz.careerguidance.applicationapi.service.test;


import kz.careerguidance.applicationapi.dto.test.QuestionDto;
import kz.careerguidance.applicationapi.entity.test.ProfessionKey;
import kz.careerguidance.applicationapi.entity.test.Question;
import kz.careerguidance.applicationapi.repository.test.ProfessionKeysRepository;
import kz.careerguidance.applicationapi.repository.test.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionsRepository questionsRepository;
    private final ProfessionKeysRepository professionKeysRepository;

    public List<Question> getAll() {
        return questionsRepository.findAll();
    }

    public void create(Question question) {
        questionsRepository.save(question);
    }

    public void createQuestions(List<QuestionDto> dtos) {

        Set<Question> questions = new LinkedHashSet<>();

        dtos.forEach(x -> {
            Optional<Question> questionOptional = questionsRepository.findByQuestion(x.getQuestion().toLowerCase().trim());
            Optional<ProfessionKey> key = professionKeysRepository.findByName(x.getKey().toLowerCase().trim());
            if (questionOptional.isEmpty()) {
                Question question = new Question();
                ProfessionKey keys = new ProfessionKey();


                question.setQuestion(x.getQuestion().toLowerCase().trim());
                question.setScore(x.getScore());
                if (key.isEmpty()) {
                    keys.setName(x.getKey().toLowerCase().trim());
                } else {
                    keys = key.get();
                }

                question.setKey(keys);
                questions.add(question);
                keys.setQuestions(questions);

                questionsRepository.saveAll(questions);
                professionKeysRepository.save(keys);
                questions.clear();

            }
        });

    }

}
