package kz.careerguidance.applicationapi.repository.test;

import kz.careerguidance.applicationapi.entity.test.ProfessionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionKeysRepository extends JpaRepository<ProfessionKey, Long> {
    List<ProfessionKey> findAllByName(String name);

    Optional<ProfessionKey> findByName(String name);
}
