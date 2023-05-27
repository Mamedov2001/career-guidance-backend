package kz.careerguidance.applicationapi.repository.university;

import kz.careerguidance.applicationapi.entity.university.Speciality;
import kz.careerguidance.applicationapi.entity.university.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversitiesRepository extends JpaRepository<University, Long> {

    Optional<University> findByName(String name);

    @Query(value = "select u.* from university u join university_speciality us on u.id = us.university_id" +
            "       join speciality s on s.id = us.speciality_id" +
            "       where s.name = :speciality", nativeQuery = true)
    List<University> findBySpecialityName(@Param("speciality") String speciality);

    List<University> findBySpecialities (Speciality speciality);

    List<University> findByNameStartingWith(String str);

}
