package kz.careerguidance.applicationapi.controller.test;

import kz.careerguidance.applicationapi.dto.test.QuestionDto;
import kz.careerguidance.applicationapi.entity.test.ProfessionKey;
import kz.careerguidance.applicationapi.repository.test.ProfessionKeysRepository;
import kz.careerguidance.applicationapi.service.test.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionsController {
    private final QuestionService questionService;
    private final ProfessionKeysRepository professionKeysRepository;

    @GetMapping("getAll")
    public ResponseEntity<List<ProfessionKey>> getAll() {
        return new ResponseEntity<>(professionKeysRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("createQuestions")
    public ResponseEntity<String> create(@RequestBody List<QuestionDto> dtos) {
        questionService.createQuestions(dtos);
        return new ResponseEntity<>("Questions was created successfully", HttpStatus.OK);
    }
}
