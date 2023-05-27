package kz.careerguidance.applicationapi.validators;

import kz.careerguidance.applicationapi.dto.university.UniversityDto;
import kz.careerguidance.applicationapi.entity.university.University;
import kz.careerguidance.applicationapi.service.impl.university.UniversitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@RequiredArgsConstructor
public class UniversityValidator implements Validator {
    private final UniversitiesService universitiesService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UniversityDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        University university = (University) target;
        if (universitiesService.findByName(university.getName()).isPresent()) {
            errors.rejectValue("name", "", "University with this name already exists");
        }
    }
}
