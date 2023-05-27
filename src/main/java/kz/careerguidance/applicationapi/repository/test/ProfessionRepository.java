package kz.careerguidance.applicationapi.repository.test;


import kz.careerguidance.applicationapi.entity.test.Profession;
import kz.careerguidance.applicationapi.entity.test.ProfessionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    List<Profession> findAllByName(String name);
    Optional<Profession> findByName(String name);
    Optional<List<Profession>> findAllByKey(ProfessionKey key);
}
