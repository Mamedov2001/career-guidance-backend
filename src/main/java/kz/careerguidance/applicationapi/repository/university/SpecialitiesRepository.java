package kz.careerguidance.applicationapi.repository.university;

import kz.careerguidance.applicationapi.entity.university.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecialitiesRepository extends JpaRepository<Speciality, Long> {
    Optional<Speciality> findByName(String name);

    List<Speciality> findByNameStartingWith(String query);
}
