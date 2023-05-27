package kz.careerguidance.applicationapi.entity.university;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Subject name is required")
    private String name;

    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.REFRESH)
    @JsonIgnore
    private List<University> universities;

}
