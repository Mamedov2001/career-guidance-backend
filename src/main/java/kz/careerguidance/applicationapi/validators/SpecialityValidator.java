package kz.careerguidance.applicationapi.validators;

import kz.careerguidance.applicationapi.dto.university.SpecialityDto;
import kz.careerguidance.applicationapi.service.impl.university.SpecialitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SpecialityValidator implements Validator {
    private final SpecialitiesService specialitiesService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SpecialityDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SpecialityDto specialityDTO = (SpecialityDto) target;

        if(specialitiesService.findByNameForCreate(specialityDTO.getName()).isPresent()) {
            errors.rejectValue("name", "", "Speciality name already exists");
        }
    }
}
