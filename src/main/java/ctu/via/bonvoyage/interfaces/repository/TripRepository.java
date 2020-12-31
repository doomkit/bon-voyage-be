package ctu.via.bonvoyage.interfaces.repository;

import ctu.via.bonvoyage.interfaces.entity.TripEntity;
import ctu.via.bonvoyage.interfaces.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, BigInteger> {
    TripEntity findByTripIdAndUser(BigInteger id, UserEntity user);
    List<TripEntity> findByUser(UserEntity user);
}
