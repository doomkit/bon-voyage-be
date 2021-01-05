package ctu.via.bonvoyage.interfaces.response;

import ctu.via.bonvoyage.interfaces.enums.TripTypeEnum;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class TripResponse {
    private BigInteger id;
    private Destination destination;
    private Route route;
    private List<Place> places;
    private TripTypeEnum tripType;
    private Date createdAt;

    static class Destination extends Place {
        private String info; // TODO
        private String picture; // TODO

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

    }

    static class Route {
        private String totalDistance;
        private String totalTime;
        private String fare;
        private List<String> instructions;
        private String points;
        private String originTitle;
        private String originAddressLabel;

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

        public List<String> getInstructions() {
            return instructions;
        }

        public void setInstructions(List<String> instructions) {
            this.instructions = instructions;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getOriginTitle() {
            return originTitle;
        }

        public void setOriginTitle(String originTitle) {
            this.originTitle = originTitle;
        }

        public String getOriginAddressLabel() {
            return originAddressLabel;
        }

        public void setOriginAddressLabel(String originAddressLabel) {
            this.originAddressLabel = originAddressLabel;
        }

    }

    static class Place {
        private String title;
        private String addressLabel;
        private String city;
        private Float lat;
        private Float lng;
        private List<String> phones; // TODO
        private List<String> webPages; // TODO
        private List<String> emails; // TODO
        private List<String> workingTime; // TODO

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddressLabel() {
            return addressLabel;
        }

        public void setAddressLabel(String addressLabel) {
            this.addressLabel = addressLabel;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Float getLng() {
            return lng;
        }

        public void setLng(Float lng) {
            this.lng = lng;
        }

        public List<String> getPhones() {
            return phones;
        }

        public void setPhones(List<String> phones) {
            this.phones = phones;
        }

        public List<String> getWebPages() {
            return webPages;
        }

        public void setWebPages(List<String> webPages) {
            this.webPages = webPages;
        }

        public List<String> getEmails() {
            return emails;
        }

        public void setEmails(List<String> emails) {
            this.emails = emails;
        }

        public List<String> getWorkingTime() {
            return workingTime;
        }

        public void setWorkingTime(List<String> workingTime) {
            this.workingTime = workingTime;
        }

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public TripTypeEnum getTripType() {
        return tripType;
    }

    public void setTripType(TripTypeEnum tripType) {
        this.tripType = tripType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
