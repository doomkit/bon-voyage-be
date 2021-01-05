package ctu.via.bonvoyage.interfaces.source;

public class RestSource {

    public static final String SIGN_UP = "public/signUp";
    public static final String SIGN_IN = "public/authenticate";
    public static final String INFO = "public/info";

    public static final String TRIP_CREATE = "secured/trip";
    public static final String TRIP_UPDATE = "secured/trip/{id}";
    public static final String TRIP_DELETE = "secured/trip/{id}";
    public static final String TRIPS_GET = "secured/trips";
    public static final String TRIP_GET = "secured/trip/{}";

}
