package ctu.via.bonvoyage.interfaces.repository;

import ctu.via.bonvoyage.interfaces.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, BigInteger> {
    List<UserEntity> findByEmailIgnoreCaseAndValidIsTrue(String email);
}
