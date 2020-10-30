package ctu.via.bonvoyage.interfaces.response.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ctu.via.bonvoyage.interfaces.enums.CategoryEnum;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlaceApiResponse {

    private List<Item> items;

    public static class Item {
        private String title;
        private String id;
        private String resultType;
        private String houseNumberType;
        private String addressBlockType;
        private String localityType;
        private String administrativeAreaType;
        private Address address;
        private Position position;
        @JsonProperty("categories")
        private List<Category> categoriesList;
        @JsonIgnore
        private List<String> categories;
        private List<FoodTypes> foodTypes;
        private Boolean houseNumberFallback;
        private List<Contacts> contacts;
        @JsonIgnore
        private String phones;
        @JsonIgnore
        private String webPages;
        @JsonIgnore
        private String emails;
        private List<OpeningHours> openingHours;
        @JsonIgnore
        private WorkingHours workingHours;
        @JsonIgnore
        private String mapPicture;
        @JsonIgnore
        private String mapLink;

        public static class Address {
            private String label;
            private String countryCode;
            private String countryName;
            private String state;
            private String county;
            private String city;
            private String district;
            @JsonProperty("subdistrict")
            private String subDistrict;
            private String street;
            private String block;
            @JsonProperty("subblock")
            private String subBlock;
            private String postalCode;
            private String houseNumber;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(String countryCode) {
                this.countryCode = countryCode;
            }

            public String getCountryName() {
                return countryName;
            }

            public void setCountryName(String countryName) {
                this.countryName = countryName;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCounty() {
                return county;
            }

            public void setCounty(String county) {
                this.county = county;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getSubDistrict() {
                return subDistrict;
            }

            public void setSubDistrict(String subDistrict) {
                this.subDistrict = subDistrict;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getBlock() {
                return block;
            }

            public void setBlock(String block) {
                this.block = block;
            }

            public String getSubBlock() {
                return subBlock;
            }

            public void setSubBlock(String subBlock) {
                this.subBlock = subBlock;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            public String getHouseNumber() {
                return houseNumber;
            }

            public void setHouseNumber(String houseNumber) {
                this.houseNumber = houseNumber;
            }

        }

        public static class Position {
            private Float lat;
            private Float lng;

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

        }

        public static class Category{
            private String id;
            private String categoryName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
                this.categoryName = CategoryEnum.fromValue(this.id);
            }

            public String getCategoryName() {
                return categoryName;
            }
        }

        public static class FoodTypes {
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

        }

        public static class Contacts {
            private List<BaseContact> phone;
            private List<BaseContact> mobile;
            private List<BaseContact> tollFree;
            private List<BaseContact> fax;
            private List<BaseContact> www;
            private List<BaseContact> email;

            public static class BaseContact {
                private String label;
                private String value;

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

            }

            public List<BaseContact> getPhone() {
                return phone;
            }

            public void setPhone(List<BaseContact> phone) {
                this.phone = phone;
            }

            public List<BaseContact> getMobile() {
                return mobile;
            }

            public void setMobile(List<BaseContact> mobile) {
                this.mobile = mobile;
            }

            public List<BaseContact> getTollFree() {
                return tollFree;
            }

            public void setTollFree(List<BaseContact> tollFree) {
                this.tollFree = tollFree;
            }

            public List<BaseContact> getFax() {
                return fax;
            }

            public void setFax(List<BaseContact> fax) {
                this.fax = fax;
            }

            public List<BaseContact> getWww() {
                return www;
            }

            public void setWww(List<BaseContact> www) {
                this.www = www;
            }

            public List<BaseContact> getEmail() {
                return email;
            }

            public void setEmail(List<BaseContact> email) {
                this.email = email;
            }

        }

        public static class OpeningHours {
            private List<String> text;
            private Boolean isOpen;
            private List<Structured> structured;

            public static class Structured {
                private String start;
                private String duration;
                private String recurrence;

                public String getStart() {
                    return start;
                }

                public void setStart(String start) {
                    this.start = start;
                }

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                public String getRecurrence() {
                    return recurrence;
                }

                public void setRecurrence(String recurrence) {
                    this.recurrence = recurrence;
                }

            }

            public List<String> getText() {
                return text;
            }

            public void setText(List<String> text) {
                this.text = text;
            }

            public Boolean getIsOpen() {
                return isOpen;
            }

            public void setIsOpen(Boolean isOpen) {
                this.isOpen = isOpen;
            }

            public List<Structured> getStructured() {
                return structured;
            }

            public void setStructured(List<Structured> structured) {
                this.structured = structured;
            }

        }

        public static class WorkingHours {
            private Boolean isWorking;
            private String workingTime;

            public Boolean getIsWorking() {
                return isWorking;
            }

            public void setIsWorking(Boolean isWorking) {
                this.isWorking = isWorking;
            }

            public String getWorkingTime() {
                return workingTime;
            }

            public void setWorkingTime(String workingTime) {
                this.workingTime = workingTime;
            }

        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getResultType() {
            return resultType;
        }

        public void setResultType(String resultType) {
            this.resultType = resultType;
        }

        public String getHouseNumberType() {
            return houseNumberType;
        }

        public void setHouseNumberType(String houseNumberType) {
            this.houseNumberType = houseNumberType;
        }

        public String getAddressBlockType() {
            return addressBlockType;
        }

        public void setAddressBlockType(String addressBlockType) {
            this.addressBlockType = addressBlockType;
        }

        public String getLocalityType() {
            return localityType;
        }

        public void setLocalityType(String localityType) {
            this.localityType = localityType;
        }

        public String getAdministrativeAreaType() {
            return administrativeAreaType;
        }

        public void setAdministrativeAreaType(String administrativeAreaType) {
            this.administrativeAreaType = administrativeAreaType;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public List<Category> getCategoriesList() {
            return categoriesList;
        }

        public void setCategoriesList(List<Category> categoriesList) {
            this.categoriesList = categoriesList;
        }

        public List<String> getCategories() {
            return categoriesList.stream().map(Category::getCategoryName)
                    .distinct().collect(Collectors.toList());
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        public List<FoodTypes> getFoodTypes() {
            return foodTypes;
        }

        public void setFoodTypes(List<FoodTypes> foodTypes) {
            this.foodTypes = foodTypes;
        }

        public Boolean getHouseNumberFallback() {
            return houseNumberFallback;
        }

        public void setHouseNumberFallback(Boolean houseNumberFallback) {
            this.houseNumberFallback = houseNumberFallback;
        }

        public List<Contacts> getContacts() {
            return contacts;
        }

        public void setContacts(List<Contacts> contacts) {
            if (contacts != null && contacts.size() >= 1){
                this.contacts = Stream.of(contacts.get(0)).collect(Collectors.toList());
            } else {
                this.contacts = new ArrayList<>();
            }
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

        public List<OpeningHours> getOpeningHours() {
            return openingHours;
        }

        public void setOpeningHours(List<OpeningHours> openingHours) {
            if (openingHours != null && openingHours.size() >= 1){
                this.openingHours = Stream.of(openingHours.get(0)).collect(Collectors.toList());
            } else {
                this.openingHours = openingHours;
            }
        }

        public WorkingHours getWorkingHours() {
            return workingHours;
        }

        public void setWorkingHours(WorkingHours workingHours) {
            this.workingHours = workingHours;
        }

        public String getMapPicture() {
            return mapPicture;
        }

        public void setMapPicture(String mapPicture) {
            this.mapPicture = mapPicture;
        }

        public String getMapLink() {
            return mapLink;
        }

        public void setMapLink(String mapLink) {
            this.mapLink = mapLink;
        }

    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
