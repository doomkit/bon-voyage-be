package ctu.via.bonvoyage.interfaces.request;

import ctu.via.bonvoyage.interfaces.enums.CategoryEnum;
import ctu.via.bonvoyage.interfaces.enums.TripTypeEnum;

import javax.validation.constraints.NotNull;

public class TripRequest {

    @NotNull
    private String origin;
    @NotNull
    private String originLat;
    @NotNull
    private String originLng;
    @NotNull
    private String destination;
    @NotNull
    private String destinationLat;
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

    public String getOriginLat() {
        return originLat;
    }

    public void setOriginLat(String originLat) {
        this.originLat = originLat;
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

    public String getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
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
