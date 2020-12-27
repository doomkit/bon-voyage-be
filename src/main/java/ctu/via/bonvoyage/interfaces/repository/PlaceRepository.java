package ctu.via.bonvoyage.interfaces.repository;

import ctu.via.bonvoyage.interfaces.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, BigInteger> {
}
