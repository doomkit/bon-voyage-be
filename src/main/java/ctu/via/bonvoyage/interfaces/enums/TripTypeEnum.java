package ctu.via.bonvoyage.interfaces.enums;

public enum TripTypeEnum {
    CAR("driving"),
    PUBLIC_TRANSPORT("transit");

    private String value;

    TripTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
