package ctu.via.bonvoyage.interfaces.enums;

public enum WeekEnum {
    MONDAY("MO", "Po"),
    TUESDAY("TU", "Út"),
    WEDNESDAY("WE", "St"),
    THURSDAY("TH", "Čt"),
    FRIDAY("FR", "Pá"),
    SATURDAY("SA", "So"),
    SUNDAY("SU", "Ne");

    private final String code;
    private final String value;

    WeekEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String fromCode(String code) {
        for (WeekEnum day : values()) {
            if (day.code.equals(code)){
                return day.value;
            }
        }
        return "";
    }

}
