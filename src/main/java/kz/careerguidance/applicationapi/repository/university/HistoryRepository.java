package kz.careerguidance.applicationapi.repository.university;

import kz.careerguidance.applicationapi.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}
