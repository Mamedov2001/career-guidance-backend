package kz.careerguidance.applicationapi.entity.university;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.careerguidance.applicationapi.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Speciality {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private Category category;

    @Column(name = "categoryImage")
    private String categoryImage;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    @Size(min = 3, max = 100, message = "Speciality name must contain between 3 and 100 characters")
    private String name;

    @Column(name = "description")
    @Size(min = 3, max = 200, message = "Speciality description must contain more than 3 characters")
    private String description;

    @Column(name = "obligation")
    @Size(min = 3, max = 200, message = "Detailed information about the speciality must contain more than 3 characters")
    private String whatMakes;

    @Column(name = "required_knowledge")
    @Size(min = 3, max = 200, message = "Required knowledge must contain more than 3 characters")
    private String requiredKnowledge;

    @Column(name = "salary_information")
    @Size(min = 3, max = 200, message = "Salary information must contain more than 3 characters")
    private String salaryInfo;

    @ManyToMany(mappedBy = "specialities", cascade = CascadeType.REFRESH)
    @JsonIgnore
    private List<University> universities;

    public Speciality(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
