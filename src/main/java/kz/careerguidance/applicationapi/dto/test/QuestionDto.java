package kz.careerguidance.applicationapi.dto.test;

import lombok.Data;

@Data
public class QuestionDto {
    private String key;
    private String question;
    private int score;
}
