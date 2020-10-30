package ctu.via.bonvoyage.interfaces.response;

import java.util.List;

public class PlaceResponse {

    private String title;
    private String addressLabel;
    private String city;
    private Float lat;
    private Float lng;
    private List<String> phones;
    private List<String> webPages;
    private List<String> emails;
    private List<String> workingTime;

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
