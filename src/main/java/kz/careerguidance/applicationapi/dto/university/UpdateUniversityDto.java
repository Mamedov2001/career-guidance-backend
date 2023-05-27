package kz.careerguidance.applicationapi.dto.university;

import kz.careerguidance.applicationapi.entity.university.Subject;
import kz.careerguidance.applicationapi.entity.university.Tag;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class UpdateUniversityDto implements Serializable {
    private Long id;

    @Size(min = 5, max = 100, message = "University name must be between 5 and 100 characters")
    private String name;

    private List<Tag> tags;

    private List<Subject> subjects;


    private String status;

    private String image;

    private String benefits;

    private String location;

    private Long passScores;

    private Long budget;

    private Long offBudget;

    private Boolean hasHotel;

    private Boolean hasMilitaryDepartment;


    @Size(min = 30, max = 500, message = "University description must be between 30 and 500 characters")
    private String description;


    private Float rating;

    private String link;
}