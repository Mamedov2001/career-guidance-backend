package kz.careerguidance.applicationapi.repository.university;

import kz.careerguidance.applicationapi.entity.university.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
