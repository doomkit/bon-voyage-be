package ctu.via.bonvoyage.interfaces.response.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ctu.via.bonvoyage.service.util.RouteUtil;

import javax.persistence.Column;
import java.util.List;

public class RouteApiResponse {

    @JsonProperty("geocoded_waypoints")
    private List<GeoCodedWaypoints> geoCodedWaypoints;
    private List<Route> routes;
    private String status;
    @JsonIgnore
    private String totalDistance;
    @JsonIgnore
    private String totalTime;
    @JsonIgnore
    private List<String> instructions;
    private String fare;
    @JsonIgnore
    private String originTitle;

    public static class GeoCodedWaypoints {
        @JsonProperty("place_id")
        private String placeId;

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }
    }

    public static class Route {
        private Fare fare;
        private List<Leg> legs;
        @JsonProperty("overview_polyline")
        private OverviewPolyline overviewPolyline;

        public static class Fare {
            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }

        public static class Leg {
            private BaseObject distance;
            private BaseObject duration;
            @JsonProperty("end_address")
            private String endAddress;
            @JsonProperty("end_location")
            private Geometry endLocation;
            @JsonProperty("start_address")
            private String startAddress;
            @JsonProperty("start_location")
            private Geometry startLocation;
            private List<Step> steps;

            public static class BaseObject {
                private String text;
                private Integer value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public Integer getValue() {
                    return value;
                }

                public void setValue(Integer value) {
                    this.value = value;
                }

            }

            public static class Step {
                private BaseObject distance;
                private BaseObject duration;
                @JsonProperty("html_instructions")
                private String htmlInstructions;
                @JsonIgnore
                private Boolean created = false;
                @JsonProperty("transit_details")
                private TransitDetails transitDetails;

                public static class TransitDetails {
                    @JsonProperty("num_stops")
                    private Integer numStops;
                    private Line line;

                    public static class Line {
                        @JsonProperty("short_name")
                        private String shortName;

                        public String getShortName() {
                            return shortName;
                        }

                        public void setShortName(String shortName) {
                            this.shortName = shortName;
                        }
                    }

                    public Integer getNumStops() {
                        return numStops;
                    }

                    public void setNumStops(Integer numStops) {
                        this.numStops = numStops;
                    }

                    public Line getLine() {
                        return line;
                    }

                    public void setLine(Line line) {
                        this.line = line;
                    }

                }

                public BaseObject getDistance() {
                    return distance;
                }

                public void setDistance(BaseObject distance) {
                    this.distance = distance;
                }

                public BaseObject getDuration() {
                    return duration;
                }

                public void setDuration(BaseObject duration) {
                    this.duration = duration;
                }

                public String getHtmlInstructions() {
                    if (!created){
                        RouteUtil.getHtmlInstructions(this);
                    }

                    return htmlInstructions;
                }

                public String getHtmlInstructionsString() {
                    return htmlInstructions;
                }

                public void setHtmlInstructions(String htmlInstructions) {
                    this.htmlInstructions = htmlInstructions;
                }

                public Boolean getCreated() {
                    return created;
                }

                public void setCreated(Boolean created) {
                    this.created = created;
                }

                public TransitDetails getTransitDetails() {
                    return transitDetails;
                }

                public void setTransitDetails(TransitDetails transitDetails) {
                    this.transitDetails = transitDetails;
                }

            }

            public static class Geometry {
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

                @Override
                public String toString(){
                    return lat + "," + lng;
                }

            }

            public BaseObject getDistance() {
                return distance;
            }

            public void setDistance(BaseObject distance) {
                this.distance = distance;
            }

            public BaseObject getDuration() {
                return duration;
            }

            public void setDuration(BaseObject duration) {
                this.duration = duration;
            }

            public List<Step> getSteps() {
                return steps;
            }

            public void setSteps(List<Step> steps) {
                this.steps = steps;
            }

            public String getEndAddress() {
                return endAddress;
            }

            public void setEndAddress(String endAddress) {
                this.endAddress = endAddress;
            }

            public Geometry getEndLocation() {
                return endLocation;
            }

            public void setEndLocation(Geometry endLocation) {
                this.endLocation = endLocation;
            }

            public String getStartAddress() {
                return startAddress;
            }

            public void setStartAddress(String startAddress) {
                this.startAddress = startAddress;
            }

            public Geometry getStartLocation() {
                return startLocation;
            }

            public void setStartLocation(Geometry startLocation) {
                this.startLocation = startLocation;
            }

        }

        public static class OverviewPolyline {
            private String points;

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

        }

        public List<Leg> getLegs() {
            return legs;
        }

        public void setLegs(List<Leg> legs) {
            this.legs = legs;
        }

        public OverviewPolyline getOverviewPolyline() {
            return overviewPolyline;
        }

        public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
            this.overviewPolyline = overviewPolyline;
        }

        public Fare getFare() {
            return fare;
        }

        public void setFare(Fare fare) {
            this.fare = fare;
        }

    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalDistance() {
        RouteUtil.getTotalDistance(this);
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getTotalTime() {
        RouteUtil.getTotalTime(this);
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public List<String> getInstructions() {
        RouteUtil.getInstructions(this);
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public List<GeoCodedWaypoints> getGeoCodedWaypoints() {
        return geoCodedWaypoints;
    }

    public void setGeoCodedWaypoints(List<GeoCodedWaypoints> geoCodedWaypoints) {
        this.geoCodedWaypoints = geoCodedWaypoints;
    }

    public String getFare() {
        RouteUtil.getFare(this);
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getOriginTitle() {
        return originTitle;
    }

    public void setOriginTitle(String originTitle) {
        this.originTitle = originTitle;
    }

}
