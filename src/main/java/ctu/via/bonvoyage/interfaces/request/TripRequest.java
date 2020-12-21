package ctu.via.bonvoyage.interfaces.request;

import ctu.via.bonvoyage.interfaces.enums.CategoryEnum;
import ctu.via.bonvoyage.interfaces.enums.TripTypeEnum;

import javax.validation.constraints.NotNull;

public class TripRequest {

    private String origin;
    @NotNull
    private String originLan;
    @NotNull
    private String originLng;
    private String destination;
    @NotNull
    private String destinationLan;
    @NotNull
    private String destinationLng;
    @NotNull
    private TripTypeEnum tripType;
    @NotNull
    private CategoryEnum category;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public TripTypeEnum getTripType() {
        if (tripType == null){
            return TripTypeEnum.CAR;
        }
        return tripType;
    }

    public void setTripType(TripTypeEnum tripType) {
        this.tripType = tripType;
    }

    public CategoryEnum getCategory() {
        if (category == null){
            return CategoryEnum.MONUMENTS;
        }
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

}
