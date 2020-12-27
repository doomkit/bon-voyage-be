package ctu.via.bonvoyage.interfaces.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@Table(name = "ROUTE_BV")
public class RouteEntity {

    @Id
    @NotNull
    @Column(name = "ROUTE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "TOTAL_DISTANCE")
    private String totalDistance;

    @Column(name = "TOTAL_TIME")
    private String totalTime;

    @Column(name = "FARE")
    private String fare;

    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @Column(name = "POINTS")
    private String points;

    @Column(name = "ORIGIN_LAN")
    private String originLan;

    @Column(name = "ORIGIN_LNG")
    private String originLng;

    @Column(name = "DESTINATION_LAN")
    private String destinationLan;

    @Column(name = "DESTINATION_LNG")
    private String destinationLng;

    @Column(name = "ORIGIN_TITLE")
    private String originTitle;

    @Column(name = "ORIGIN_ADDRESS_LABEL")
    private String originAddressLabel;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getOriginLan() {
        return originLan;
    }

    public void setOriginLan(String originLan) {
        this.originLan = originLan;
    }

    public String getOriginLng() {
        return originLng;
    }

    public void setOriginLng(String originLng) {
        this.originLng = originLng;
    }

    public String getDestinationLan() {
        return destinationLan;
    }

    public void setDestinationLan(String destinationLan) {
        this.destinationLan = destinationLan;
    }

    public String getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(String destinationLng) {
        this.destinationLng = destinationLng;
    }

}
