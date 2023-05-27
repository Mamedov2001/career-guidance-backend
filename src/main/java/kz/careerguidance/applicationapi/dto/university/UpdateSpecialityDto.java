package kz.careerguidance.applicationapi.dto.university;

import kz.careerguidance.applicationapi.entity.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UpdateSpecialityDto implements Serializable {

    private Category category;

    private String categoryImage;

    private String image;

    @Size(min = 3, max = 100, message = "Speciality name must contain between 3 and 100 characters")
    private final String name;

    @Size(min = 20, max = 200, message = "Speciality description must contain between 20 and 200 characters")
    private final String description;

    @Size(min = 3, max = 200, message = "Detailed information about the speciality must contain more than 3 characters")
    private String whatMakes;

    @Size(min = 3, max = 200, message = "Required knowledge must contain more than 3 characters")
    private String requiredKnowledge;

    @Size(min = 3, max = 200, message = "Salary information must contain more than 3 characters")
    private String salaryInfo;
}