package kz.careerguidance.applicationapi.entity.university;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class University {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 5, max = 100, message = "University name must be between 5 and 100 characters")
    private String name;

    @Column(name = "status")
    private String status;


    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "university_subject",
            joinColumns = @JoinColumn(name = "university_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "university_tag",
            joinColumns = @JoinColumn(name = "university_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    @Size(min = 30, max = 500, message = "University description must be between 30 and 500 characters")
    private String description;


    @Column(name = "benefits")
    private String benefits;

    @Column(name = "location")
    private String location;

    @Column(name = "pass_scores")
    private Long passScores;

    @Column(name = "budget")
    private Long budget;

    @Column(name = "off_budget")
    private Long offBudget;

    @Column(name = "hasHotel")
    private Boolean hasHotel;

    @Column(name = "hasMilitaryDepartment")
    private Boolean hasMilitaryDepartment;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "link")
    private String link;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "university_speciality",
            joinColumns = @JoinColumn(name = "university_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private List<Speciality> specialities;

}
