package ctu.via.bonvoyage.interfaces.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@Table(name = "PLACE_BV")
public class PlaceEntity {

    @Id
    @NotNull
    @Column(name = "PLACE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ADDRESS_LABEL")
    private String addressLabel;

    @Column(name = "CITY")
    private String city;

    @Column(name = "LAT")
    private String lat;

    @Column(name = "LNG")
    private String lng;

    @Column(name = "PHONES")
    private String phones;

    @Column(name = "WEB_PAGES")
    private String webPages;

    @Column(name = "EMAILS")
    private String emails;

    @Column(name = "WORKING_TIME")
    private String workingTime;

    @Column(name = "INFO")
    private String info;

    @Column(name = "PICTURE")
    private String picture;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getWebPages() {
        return webPages;
    }

    public void setWebPages(String webPages) {
        this.webPages = webPages;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

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
