package kz.careerguidance.applicationapi.dto.university;

import kz.careerguidance.applicationapi.entity.university.Subject;
import kz.careerguidance.applicationapi.entity.university.Tag;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class UniversityDto implements Serializable {
    private Long id;

    @NotNull(message = "University name is required")
    @Size(min = 5, max = 100, message = "University name must be between 5 and 100 characters")
    private String name;

    private List<Tag> tags;

    private List<Subject> subjects;


    @NotNull(message = "University status is required")
    private String status;

    private String image;

    @NotNull(message = "University benefits are required")
    private String benefits;

    @NotNull(message = "University location are required")
    private String location;

    @NotNull(message = "University pass scores are required")
    private Long passScores;

    @NotNull(message = "University budget is required")
    private Long budget;

    @NotNull(message = "University offbudget is required")
    private Long offBudget;

    @NotNull(message = "University hotel existing status is required")
    private Boolean hasHotel;

    @NotNull(message = "University military existing status is required")
    private Boolean hasMilitaryDepartment;


    @NotNull(message = "University description is required")
    @Size(min = 30, max = 500, message = "University description must be between 30 and 500 characters")
    private String description;


    @NotNull(message = "University rating is required")
    private Float rating;

    @NotNull(message = "University link is required")
    private String link;
}