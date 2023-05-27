package kz.careerguidance.applicationapi.repository.university;

import kz.careerguidance.applicationapi.entity.university.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
