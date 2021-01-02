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
    private BigInteger placeId;

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
    private String phone;

    @Column(name = "WEB_PAGES")
    private String webPage;

    @Column(name = "EMAILS")
    private String email;

    @Column(name = "WORKING_TIME")
    private String time;

    @Column(name = "INFO")
    private String info;

    @Column(name = "PICTURE")
    private String picture;

    public BigInteger getPlaceId() {
        return placeId;
    }

    public void setPlaceId(BigInteger placeId) {
        this.placeId = placeId;
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

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
