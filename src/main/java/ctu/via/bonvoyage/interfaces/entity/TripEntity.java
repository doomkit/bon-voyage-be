package ctu.via.bonvoyage.interfaces.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TRIP_BV")
public class TripEntity {

    @Id
    @NotNull
    @Column(name = "TRIP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger tripId;

    @ManyToOne
    @JoinColumn(name = "EMAIL")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "PLACE_ID")
    private PlaceEntity destination;

    @OneToOne
    @JoinColumn(name = "ROUTE_ID")
    private RouteEntity route;

    @OneToMany
    @JoinColumn(name = "TRIP_ID")
    private List<PlaceEntity> places;

    @NotNull
    @Column(name = "TRIP_TYPE")
    private String tripType;

    @NotNull
    @Column(name = "CREATED_AT")
    private Date createdAt;

    public BigInteger getTripId() {
        return tripId;
    }

    public void setTripId(BigInteger tripId) {
        this.tripId = tripId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PlaceEntity getDestination() {
        return destination;
    }

    public void setDestination(PlaceEntity destination) {
        this.destination = destination;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }

    public List<PlaceEntity> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceEntity> places) {
        this.places = places;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
