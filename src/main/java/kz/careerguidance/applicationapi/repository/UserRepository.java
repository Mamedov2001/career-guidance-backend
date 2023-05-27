package kz.careerguidance.applicationapi.repository;

import kz.careerguidance.applicationapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String login);
  Optional<UserEntity> findByEmail(String email);

}
