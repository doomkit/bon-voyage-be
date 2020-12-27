package ctu.via.bonvoyage.interfaces.repository;

import ctu.via.bonvoyage.interfaces.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, BigInteger> {
    TripEntity findByTripId(BigInteger id);
}
