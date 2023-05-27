package kz.careerguidance.applicationapi.entity.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class ProfessionKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "key")
    @ToString.Exclude
    @JsonIgnore
    private Set<Profession> professions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "key")
    @ToString.Exclude
    private Set<Question> questions = new LinkedHashSet<>();
}
